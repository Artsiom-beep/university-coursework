"""
GOOGLE QUANTILES - BEGINNER GUIDE
=================================

This file teaches pandas .quantile() from the beginning.

Main idea:
    A quantile is a cutoff value in sorted data.

Examples:
    q = 0.25  -> 25th percentile -> Q1
    q = 0.50  -> 50th percentile -> median -> Q2
    q = 0.75  -> 75th percentile -> Q3

IMPORTANT:
    q must be between 0 and 1.

    0.00 = minimum
    0.25 = first quartile (Q1)
    0.50 = median (Q2)
    0.75 = third quartile (Q3)
    1.00 = maximum

This guide is written for pandas 3.x and NumPy 2.x.
"""

from pathlib import Path

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd


# =============================================================================
# 0. OPTIONAL: LOAD THE UNICORN DATASET
# =============================================================================

# __file__ = the path of this Python file.
#
# Path(__file__) creates a Path object from the file path.
# .resolve() gives us the full absolute path.
# .parent gives us the folder where this Python file is located.
#
# Example:
# C:/Users/marke/PycharmProjects/PythonProject/GoogleCourse/GoogleQuantiles.py
#
# SCRIPT_DIR would be:
# C:/Users/marke/PycharmProjects/PythonProject/GoogleCourse

SCRIPT_DIR = Path(__file__).resolve().parent


# We prepare several possible locations for the CSV file.
#
# First option:
# The CSV is in the same folder as this Python file.
#
# Second option:
# The project looks like this:
#
# PythonProject/
#     Datasets/
#         Modified_Unicorn_Companies.csv
#
#     GoogleCourse/
#         GoogleQuantiles.py

possible_paths = [
    SCRIPT_DIR / "Modified_Unicorn_Companies.csv",
    SCRIPT_DIR.parent / "Datasets" / "Modified_Unicorn_Companies.csv",
]


# We start with companies = None.
#
# None means:
# "We have not loaded the dataset yet."

companies = None


# Try every possible path.
for path in possible_paths:

    # .exists() checks whether the file exists.
    if path.exists():

        # If the file exists, load it.
        companies = pd.read_csv(path)

        # Stop the loop because we already found the file.
        break


# =============================================================================
# 1. WHAT DOES quantile() DO?
# =============================================================================

print("\n--- 1. BASIC IDEA ---")


# Create a pandas Series.
#
# A Series is basically one column of data.
#
# Underscores inside numbers are only for readability.
#
# 100_000 is exactly the same as 100000.

prices = pd.Series([
    100_000,
    200_000,
    300_000,
    600_000
])


# .quantile(0.75) asks:
#
# "What value is at the 75th percentile?"
#
# q = 0.75 means 75%.

q75 = prices.quantile(0.75)


print("Prices:")
print(prices.tolist())

print("75th percentile:")
print(q75)


# RESULT:
#
# 375000.0
#
# Notice:
#
# 375000 does NOT exist in our original data:
#
# 100000
# 200000
# 300000
# 600000
#
# pandas calculated a value BETWEEN 300000 and 600000.
#
# This happens because pandas uses linear interpolation by default.


# =============================================================================
# 2. ONE QUANTILE VS MANY QUANTILES
# =============================================================================

print("\n--- 2. ONE QUANTILE VS MANY ---")


# -------------------------------------------------------------------------
# ONE QUANTILE
# -------------------------------------------------------------------------

# Here we ask for one quantile:
#
# 0.50 = 50th percentile = median

one_quantile = prices.quantile(0.50)


print("One quantile:")
print(one_quantile)


print("Returned type:")
print(type(one_quantile))


# A single quantile returns ONE scalar value.
#
# In many cases the type is:
#
# numpy.float64
#
# Example:
#
# <class 'numpy.float64'>
#
# You can normally use it like a regular Python number.

print(one_quantile + 100)


# -------------------------------------------------------------------------
# MULTIPLE QUANTILES
# -------------------------------------------------------------------------

# We can give quantile() a list of q values.
#
# Here we ask for:
#
# Q1     = 25th percentile
# Median = 50th percentile
# Q3     = 75th percentile

many_quantiles = prices.quantile([
    0.25,
    0.50,
    0.75
])


print("\nMany quantiles:")
print(many_quantiles)


print("Returned type:")
print(type(many_quantiles))


# When we request MANY quantiles from a Series,
# pandas returns another Series.
#
# Example:
#
# 0.25    175000.0
# 0.50    250000.0
# 0.75    375000.0
#
# The LEFT side is the index:
#
# 0.25
# 0.50
# 0.75
#
# The RIGHT side contains the calculated values.


# We can access one result using its index.

print("\nMedian from the result:")
print(many_quantiles.loc[0.50])


# =============================================================================
# 3. HOW DEFAULT LINEAR INTERPOLATION WORKS
# =============================================================================

print("\n--- 3. LINEAR INTERPOLATION ---")


# pandas uses:
#
# interpolation='linear'
#
# by default.
#
# Interpolation is needed when the requested percentile
# falls BETWEEN two real values.

demo = pd.Series([
    10,
    20,
    30,
    40
])


# Ask for the 40th percentile.

result = demo.quantile(0.40)


print("Data:")
print(demo.tolist())


print("40th percentile:")
print(result)


# RESULT:
#
# 22.0


# -------------------------------------------------------------------------
# HOW DID PANDAS GET 22?
# -------------------------------------------------------------------------

# STEP 1
#
# Sort the values:
#
# [10, 20, 30, 40]
#
#
# Their positions are:
#
# index 0 -> 10
# index 1 -> 20
# index 2 -> 30
# index 3 -> 40


# STEP 2
#
# Calculate the virtual position.
#
# For pandas DEFAULT linear interpolation:
#
# position = q * (n - 1)
#
# q = requested quantile
# n = number of values
#
# Our example:
#
# q = 0.40
# n = 4
#
# position = 0.40 * (4 - 1)
#
# position = 0.40 * 3
#
# position = 1.2


# STEP 3
#
# Position 1.2 is between:
#
# index 1 -> value 20
#
# and
#
# index 2 -> value 30


# STEP 4
#
# 1.2 means:
#
# Start at position 1.
#
# Then move 20% toward position 2.
#
# Difference between values:
#
# 30 - 20 = 10
#
# 20% of 10:
#
# 10 * 0.2 = 2
#
# Add this to 20:
#
# 20 + 2 = 22
#
# RESULT:
#
# 22.0


# IMPORTANT:
#
# This explanation describes the DEFAULT:
#
# interpolation='linear'
#
# Other interpolation methods use different rules.


# =============================================================================
# 4. PANDAS INTERPOLATION OPTIONS
# =============================================================================

print("\n--- 4. INTERPOLATION OPTIONS ---")


# Series.quantile() has the parameter:
#
# interpolation=
#
# Available values:
#
# 'linear'
# 'lower'
# 'higher'
# 'nearest'
# 'midpoint'


# Our data:
#
# [10, 20, 30, 40]
#
# q = 0.40
#
# The target position is between 20 and 30.


# -------------------------------------------------------------------------
# LINEAR
# -------------------------------------------------------------------------

linear_result = demo.quantile(
    0.40,
    interpolation="linear"
)

print("linear:")
print(linear_result)


# linear calculates a value between the neighbors.
#
# Result:
#
# 22.0


# -------------------------------------------------------------------------
# LOWER
# -------------------------------------------------------------------------

lower_result = demo.quantile(
    0.40,
    interpolation="lower"
)

print("\nlower:")
print(lower_result)


# lower chooses the LOWER neighboring value.
#
# Between:
#
# 20 and 30
#
# Result:
#
# 20


# -------------------------------------------------------------------------
# HIGHER
# -------------------------------------------------------------------------

higher_result = demo.quantile(
    0.40,
    interpolation="higher"
)

print("\nhigher:")
print(higher_result)


# higher chooses the HIGHER neighboring value.
#
# Between:
#
# 20 and 30
#
# Result:
#
# 30


# -------------------------------------------------------------------------
# NEAREST
# -------------------------------------------------------------------------

nearest_result = demo.quantile(
    0.40,
    interpolation="nearest"
)

print("\nnearest:")
print(nearest_result)


# nearest chooses the nearest real data point.


# -------------------------------------------------------------------------
# MIDPOINT
# -------------------------------------------------------------------------

midpoint_result = demo.quantile(
    0.40,
    interpolation="midpoint"
)

print("\nmidpoint:")
print(midpoint_result)


# midpoint calculates:
#
# (lower + higher) / 2
#
# (20 + 30) / 2
#
# 25


# -------------------------------------------------------------------------
# COMPACT VERSION USING A LOOP
# -------------------------------------------------------------------------

print("\nAll interpolation methods:")


interpolation_methods = [
    "linear",
    "lower",
    "higher",
    "nearest",
    "midpoint"
]


for interpolation_method in interpolation_methods:

    value = demo.quantile(
        0.40,
        interpolation=interpolation_method
    )

    print(
        f"{interpolation_method:8} -> {value}"
    )


# Expected result:
#
# linear   -> 22.0
# lower    -> 20
# higher   -> 30
# nearest  -> 20
# midpoint -> 25.0


# =============================================================================
# 5. DATAFRAME.quantile()
# =============================================================================

print("\n--- 5. DATAFRAME QUANTILES ---")


# So far we used Series.quantile().
#
# Now we use DataFrame.quantile().
#
# A DataFrame contains multiple columns.

employees = pd.DataFrame({

    "Revenue": [
        100,
        200,
        300,
        400,
        500
    ],

    "Employees": [
        10,
        25,
        50,
        75,
        100
    ],

    "City": [
        "Minsk",
        "Brest",
        "Gomel",
        "Grodno",
        "Vitebsk"
    ]
})


print("Original DataFrame:")
print(employees)


# -------------------------------------------------------------------------
# SINGLE QUANTILE
# -------------------------------------------------------------------------

# DataFrame.quantile() calculates quantiles
# for columns.
#
# Our DataFrame contains:
#
# Revenue    -> numeric
# Employees  -> numeric
# City       -> text
#
# We use:
#
# numeric_only=True
#
# because we only want numeric columns.

median_per_column = employees.quantile(
    0.50,
    numeric_only=True
)


print("\nMedian of each numeric column:")
print(median_per_column)


# Result:
#
# Revenue      300.0
# Employees     50.0
#
#
# One q value on a DataFrame
# returns a Series.
#
# The index contains column names:
#
# Revenue
# Employees


# -------------------------------------------------------------------------
# MULTIPLE QUANTILES
# -------------------------------------------------------------------------

quartiles_per_column = employees.quantile(

    [
        0.25,
        0.50,
        0.75
    ],

    numeric_only=True
)


print("\nQ1, median, and Q3:")
print(quartiles_per_column)


# Result structure:
#
#       Revenue  Employees
#
# 0.25    ...
# 0.50    ...
# 0.75    ...
#
#
# Rows:
#
# requested quantiles
#
#
# Columns:
#
# original numeric DataFrame columns


# =============================================================================
# 6. axis=0 VS axis=1
# =============================================================================

print("\n--- 6. AXIS ---")


# Select only numeric columns.

numeric_df = employees[
    [
        "Revenue",
        "Employees"
    ]
]


print("Numeric DataFrame:")
print(numeric_df)


# -------------------------------------------------------------------------
# axis=0
# -------------------------------------------------------------------------

# axis=0 means:
#
# calculate DOWN the rows
# for EACH COLUMN.
#
# This is the DEFAULT.

column_medians = numeric_df.quantile(
    0.50,
    axis=0
)


print("\nMedian down each column:")
print(column_medians)


# This asks:
#
# What is the median Revenue?
#
# What is the median Employees value?


# -------------------------------------------------------------------------
# axis=1
# -------------------------------------------------------------------------

# axis=1 means:
#
# calculate ACROSS columns
# for EACH ROW.

row_medians = numeric_df.quantile(
    0.50,
    axis=1
)


print("\nMedian across each row:")
print(row_medians)


# Example first row:
#
# Revenue = 100
# Employees = 10
#
# Median of:
#
# [100, 10]
#
# is:
#
# 55


# axis=1 is usually less common
# for normal statistical quantile analysis.


# =============================================================================
# 7. IMPORTANT: method= IN DataFrame.quantile()
# =============================================================================

print("\n--- 7. DATAFRAME method= PARAMETER ---")


# THIS WAS THE MAIN ERROR IN THE ORIGINAL FILE.
#
#
# You had:
#
# df.quantile(
#     0.40,
#     numeric_only=True,
#     method='inverted_cdf'
# )
#
#
# This produces:
#
# ValueError:
#
# Invalid method: inverted_cdf.
# Method must be in {'table', 'single'}.


# WHY?
#
# Because pandas DataFrame.quantile() uses:
#
# method=
#
# for a completely different purpose.
#
#
# Pandas DataFrame.quantile() supports:
#
# method='single'
#
# method='table'
#
#
# It does NOT support:
#
# method='inverted_cdf'


# 'inverted_cdf' is a NumPy quantile method.
#
# We will see that later.


# -------------------------------------------------------------------------
# method='single'
# -------------------------------------------------------------------------

# This is the DEFAULT.
#
# Each column is calculated independently.

single_result = numeric_df.quantile(
    0.40,
    method="single",
    interpolation="linear"
)


print("method='single':")
print(single_result)


# Usually you do NOT need to write:
#
# method='single'
#
# because it is already the default.
#
# This is enough:
#
# numeric_df.quantile(0.40)


# -------------------------------------------------------------------------
# method='table'
# -------------------------------------------------------------------------

# method='table' is more advanced.
#
# It calculates quantiles over the table together.
#
# With method='table',
# pandas only allows:
#
# interpolation='nearest'
# interpolation='lower'
# interpolation='higher'
#
#
# It does NOT allow:
#
# interpolation='linear'
#
# or:
#
# interpolation='midpoint'


table_result = numeric_df.quantile(
    0.40,
    method="table",
    interpolation="nearest"
)


print("\nmethod='table':")
print(table_result)


# FOR BEGINNER DATA ANALYSIS:
#
# Normally use the default:
#
# method='single'
#
# You usually do not need method='table'.


# =============================================================================
# 8. MISSING VALUES: NaN
# =============================================================================

print("\n--- 8. NaN VALUES ---")


# NaN means:
#
# Not a Number
#
# It usually represents a missing value.

values_with_nan = pd.Series([
    10,
    np.nan,
    30,
    40,
    50
])


print("Data:")
print(values_with_nan.tolist())


# pandas quantile() ignores NaN values automatically.

median_with_nan = values_with_nan.quantile(
    0.50
)


print("Median:")
print(median_with_nan)


# IMPORTANT ERROR FROM THE ORIGINAL FILE:
#
# This is WRONG:
#
# values_with_nan.quantile(
#     0.50,
#     skipna=True
# )
#
#
# Series.quantile() does NOT have
# a skipna= parameter.
#
#
# You simply write:
#
# values_with_nan.quantile(0.50)


# -------------------------------------------------------------------------
# WHAT IF EVERYTHING IS NaN?
# -------------------------------------------------------------------------

all_nan = pd.Series([
    np.nan,
    np.nan,
    np.nan
])


all_nan_result = all_nan.quantile(
    0.50
)


print("\nAll values are NaN:")
print(all_nan_result)


# Result:
#
# NaN
#
# pandas cannot calculate a quantile
# because there are no real numeric values.


# =============================================================================
# 9. GROUPBY + quantile()
# =============================================================================

print("\n--- 9. GROUPBY + QUANTILE ---")


# groupby() allows us to divide data into groups.
#
# Then we can calculate a quantile
# separately for each group.

salaries = pd.DataFrame({

    "Department": [
        "IT",
        "IT",
        "IT",

        "HR",
        "HR",
        "HR",

        "Sales",
        "Sales",
        "Sales"
    ],

    "Salary": [
        5000,
        7000,
        9000,

        3000,
        3500,
        4000,

        4000,
        5500,
        8000
    ]
})


print("Salary DataFrame:")
print(salaries)


# -------------------------------------------------------------------------
# MEDIAN FOR EACH DEPARTMENT
# -------------------------------------------------------------------------

# Question:
#
# "What is the median salary
# inside each department?"

median_by_department = (
    salaries
    .groupby("Department")["Salary"]
    .quantile(0.50)
)


print("\nMedian salary by department:")
print(median_by_department)


# What happens step by step:
#
#
# salaries
#
#     Our DataFrame
#
#
# .groupby("Department")
#
#     Divide rows into groups:
#
#     IT
#     HR
#     Sales
#
#
# ["Salary"]
#
#     Select only the Salary column
#
#
# .quantile(0.50)
#
#     Calculate median inside each group


# -------------------------------------------------------------------------
# MULTIPLE QUANTILES FOR EACH GROUP
# -------------------------------------------------------------------------

quartiles_by_department = (
    salaries
    .groupby("Department")["Salary"]
    .quantile([
        0.25,
        0.50,
        0.75
    ])
)


print("\nQuartiles by department:")
print(quartiles_by_department)


# The result uses a MultiIndex.
#
# Example:
#
# Department
# HR       0.25    ...
#          0.50    ...
#          0.75    ...
#
# IT       0.25    ...
#          0.50    ...
#          0.75    ...
#
#
# First index level:
#
# Department
#
#
# Second index level:
#
# Quantile


# =============================================================================
# 10. IQR: A VERY COMMON USE OF QUANTILES
# =============================================================================

print("\n--- 10. IQR AND OUTLIERS ---")


# Quantiles are often used to detect
# possible outliers.
#
# Outlier = a value that is very far
# from most other values.

scores = pd.Series([
    55,
    62,
    71,
    78,
    85,
    90,
    95,
    100,
    250
])


print("Scores:")
print(scores.tolist())


# Calculate Q1.

q1 = scores.quantile(
    0.25
)


# Calculate Q3.

q3 = scores.quantile(
    0.75
)


# IQR means:
#
# Interquartile Range
#
# Formula:
#
# IQR = Q3 - Q1

iqr = q3 - q1


print("Q1:")
print(q1)


print("Q3:")
print(q3)


print("IQR:")
print(iqr)


# IQR measures the spread
# of the middle 50% of the data.


# -------------------------------------------------------------------------
# OUTLIER BOUNDARIES
# -------------------------------------------------------------------------

# A common statistical rule:
#
# Lower boundary:
#
# Q1 - 1.5 * IQR
#
#
# Upper boundary:
#
# Q3 + 1.5 * IQR


lower_boundary = q1 - 1.5 * iqr


upper_boundary = q3 + 1.5 * iqr


print("Lower boundary:")
print(lower_boundary)


print("Upper boundary:")
print(upper_boundary)


# Now find values outside these boundaries.

outliers = scores[
    (scores < lower_boundary)
    |
    (scores > upper_boundary)
]


print("Possible outliers:")
print(outliers.tolist())


# In our example:
#
# 250
#
# will probably be detected as an outlier.


# =============================================================================
# 11. quantile() VS qcut()
# =============================================================================

print("\n--- 11. quantile() VS qcut() ---")


clean_scores = pd.Series([
    55,
    62,
    71,
    78,
    85,
    90,
    95,
    100
])


# -------------------------------------------------------------------------
# quantile()
# -------------------------------------------------------------------------

# quantile() asks:
#
# "What is the VALUE at this percentile?"
#
# It returns a cutoff value.

cutoff = clean_scores.quantile(
    0.75
)


print("75th percentile cutoff:")
print(cutoff)


# -------------------------------------------------------------------------
# qcut()
# -------------------------------------------------------------------------

# qcut() asks:
#
# "Which quantile GROUP
# does every value belong to?"
#
# Here:
#
# q=4
#
# means divide the data
# into 4 approximately equal groups.


quartile_groups = pd.qcut(

    clean_scores,

    q=4,

    labels=[
        "Q1",
        "Q2",
        "Q3",
        "Q4"
    ]
)


result_df = pd.DataFrame({

    "Score":
        clean_scores,

    "Quartile":
        quartile_groups
})


print("\nEach score's quartile group:")
print(result_df)


# REMEMBER:
#
# quantile()
#
#     gives you a cutoff VALUE
#
#
# qcut()
#
#     gives every ROW a category/group


# =============================================================================
# 12. VISUALIZING QUARTILES WITH A BOXPLOT
# =============================================================================

print("\n--- 12. BOXPLOT ---")


# A boxplot is closely connected
# to quantiles.
#
# A standard boxplot usually shows:
#
# Q1
#
# Median
#
# Q3
#
# IQR
#
# Possible outliers


plt.figure(
    figsize=(8, 4)
)


plt.boxplot(
    clean_scores,
    vert=False
)


plt.title(
    "Boxplot of Scores"
)


plt.xlabel(
    "Score"
)


plt.tight_layout()


# Save the chart
# in the same folder as this Python file.

plt.savefig(
    SCRIPT_DIR / "boxplot_quantiles.png"
)


# Close the figure.
#
# This is useful when a script creates
# multiple charts.

plt.close()


print(
    "Saved: boxplot_quantiles.png"
)


# Check quartiles manually.

print(
    "Q1:",
    clean_scores.quantile(0.25)
)


print(
    "Median:",
    clean_scores.quantile(0.50)
)


print(
    "Q3:",
    clean_scores.quantile(0.75)
)


# =============================================================================
# 13. REAL DATASET EXAMPLE
# =============================================================================

print("\n--- 13. REAL DATASET ---")


# Check whether the CSV was loaded.

if companies is None:

    print(
        "Dataset was not found."
    )

    print(
        "Put Modified_Unicorn_Companies.csv "
        "in one of these locations:"
    )

    for path in possible_paths:

        print(
            " -",
            path
        )


else:

    print(
        "Dataset loaded successfully."
    )


    # Check that the column exists.

    if "Valuation" in companies.columns:


        # Calculate:
        #
        # 10th percentile
        # Q1
        # median
        # Q3
        # 90th percentile

        valuation_quantiles = (
            companies["Valuation"]
            .quantile([
                0.10,
                0.25,
                0.50,
                0.75,
                0.90
            ])
        )


        print(
            "\nValuation quantiles:"
        )


        print(
            valuation_quantiles
        )


    else:

        print(
            "Column 'Valuation' was not found."
        )


        print(
            "Available columns:"
        )


        print(
            companies.columns.tolist()
        )


# =============================================================================
# 14. NUMPY quantile(): ADVANCED METHODS
# =============================================================================

print("\n--- 14. NUMPY QUANTILE METHODS ---")


# pandas and NumPy both have quantile functions.
#
# BUT their APIs are different.


# PANDAS SERIES:
#
# series.quantile(
#     q,
#     interpolation='lower'
# )


# NUMPY:
#
# np.quantile(
#     array,
#     q,
#     method='inverted_cdf'
# )


# This difference caused the error
# in the original code.


array = np.array([
    10,
    20,
    30,
    40
])


# -------------------------------------------------------------------------
# NUMPY: LINEAR
# -------------------------------------------------------------------------

numpy_linear = np.quantile(

    array,

    0.40,

    method="linear"
)


print(
    "NumPy linear:",
    numpy_linear
)


# -------------------------------------------------------------------------
# NUMPY: INVERTED CDF
# -------------------------------------------------------------------------

numpy_inverted_cdf = np.quantile(

    array,

    0.40,

    method="inverted_cdf"
)


print(
    "NumPy inverted_cdf:",
    numpy_inverted_cdf
)


# -------------------------------------------------------------------------
# NUMPY: CLOSEST OBSERVATION
# -------------------------------------------------------------------------

numpy_closest = np.quantile(

    array,

    0.40,

    method="closest_observation"
)


print(
    "NumPy closest_observation:",
    numpy_closest
)


# -------------------------------------------------------------------------
# NUMPY: AVERAGED INVERTED CDF
# -------------------------------------------------------------------------

numpy_averaged = np.quantile(

    array,

    0.40,

    method="averaged_inverted_cdf"
)


print(
    "NumPy averaged_inverted_cdf:",
    numpy_averaged
)


# IMPORTANT:
#
# Do NOT assume that:
#
# pandas:
#
#     method=
#
# and:
#
# NumPy:
#
#     method=
#
# mean the same thing.
#
#
# In pandas DataFrame.quantile():
#
# method=
#
# accepts:
#
# 'single'
# 'table'
#
#
# In NumPy np.quantile():
#
# method=
#
# selects the statistical algorithm
# used to calculate the quantile.


# =============================================================================
# 15. COMMON MISTAKES
# =============================================================================

print("\n--- 15. COMMON MISTAKES ---")


print(
    """
1. WRONG q RANGE
----------------

WRONG:

series.quantile(75)

Because q must be between 0 and 1.


CORRECT:

series.quantile(0.75)



2. WRONG PARAMETER FOR PANDAS INTERPOLATION
--------------------------------------------

WRONG:

series.quantile(
    0.40,
    method='lower'
)


CORRECT:

series.quantile(
    0.40,
    interpolation='lower'
)



3. USING NUMPY METHOD INSIDE PANDAS
------------------------------------

WRONG:

df.quantile(
    0.40,
    method='inverted_cdf'
)


CORRECT NUMPY VERSION:

np.quantile(
    array,
    0.40,
    method='inverted_cdf'
)



4. DATAFRAME WITH TEXT COLUMNS
-------------------------------

Use:

df.quantile(
    0.50,
    numeric_only=True
)



5. skipna= WITH Series.quantile()
----------------------------------

WRONG:

series.quantile(
    0.50,
    skipna=True
)


Series.quantile() does not have
a skipna parameter.

NaN values are ignored automatically.



6. CONFUSING quantile() AND qcut()
-----------------------------------

quantile():

Find a cutoff value.


qcut():

Divide rows into quantile groups.
"""
)


# =============================================================================
# 16. QUICK REFERENCE
# =============================================================================

print("\n--- 16. QUICK REFERENCE ---")


print(
    """
==================================================
SERIES
==================================================

s.quantile(0.50)

One quantile.
Returns one scalar value.



s.quantile(
    [0.25, 0.50, 0.75]
)

Multiple quantiles.
Returns a pandas Series.



s.quantile(
    0.40,
    interpolation='lower'
)

Control what pandas does
when the quantile falls
between two data points.



==================================================
DATAFRAME
==================================================

df.quantile(
    0.50,
    numeric_only=True
)

Calculate a quantile
for every numeric column.



df.quantile(
    [0.25, 0.75],
    numeric_only=True
)

Calculate multiple quantiles
for every numeric column.



method='single'

Default DataFrame behavior.

Columns are calculated
independently.



method='table'

Advanced DataFrame behavior.

Only these interpolation methods
are allowed:

lower
higher
nearest



==================================================
GROUPBY
==================================================

df.groupby(
    'Group'
)['Value'].quantile(
    0.50
)

Calculate a quantile
inside every group.



==================================================
NUMPY
==================================================

np.quantile(
    array,
    0.40,
    method='inverted_cdf'
)

NumPy supports additional
statistical quantile methods.



==================================================
IMPORTANT VALUES
==================================================

0.00 = minimum

0.25 = Q1

0.50 = median = Q2

0.75 = Q3

1.00 = maximum



==================================================
IQR
==================================================

IQR = Q3 - Q1


Possible outlier boundaries:

lower =
Q1 - 1.5 * IQR


upper =
Q3 + 1.5 * IQR



==================================================
MAIN IDEA
==================================================

quantile()

asks:

"What VALUE separates
this percentage of the data?"


qcut()

asks:

"Which quantile GROUP
does each row belong to?"
"""
)