import pandas as pd
import matplotlib.pyplot as plt

# a = pd.DataFrame({'a':[1,2,3], 'b':[1,5,3]})
# b = pd.Series(['a','b','c'], index = ["First","Second","Third"], name = "Letters")
# print(a)
# print(b)

studentsTest = pd.read_csv("students.csv", index_col = 0)
students = pd.read_csv("students_big.csv", index_col = "id") #we can do both as a position and as name
orders_list = pd.read_csv("retail_orders_1000.csv", index_col = 0)
# print(studentsTest.shape)
#print(students.shape)

# #iloc and loc work row-first, column-second
# #iloc is for numbers - students.iloc[0, 1]
# #loc is for - students.loc[:, "name"] -> fan fact: here students.loc[0, "name" would also work as we have id as index
# print(studentsTest.columns)
# print(studentsTest.iloc[:, 2])
#
# print(studentsTest.loc[1]) # it did write my data
# print(studentsTest.loc[1, ("name", "age")]) #but we can give print(studentsTest.loc[1, ["name", "age"]]) as well



#index manipulation

#Now id works as our index - we can change it

#studentsTest.set_index("name", inplace = True) #inplace = True says that we shouldn't create new table or rewrite the old one. Without it our code would look - studentsTest = studentsTest.set_index("name", inplace = True)
# when we rewrite index, old index gets deleted - that's why it returns new table

#also we can set as index multiple columns
#studentsTest.set_index(["name", "age"], inplace = True)
#print(studentsTest)

#print(students.loc[((students["name"].str.len() > 8) | ((students.course == "Data Analysis") & (students.final_grade > 65))) & (students.city.isin(["Poznan", "Warsaw", "Gdansk"]))].sort_values("name").to_string())



#The filter is too long - we can throw it out of print

# name_is_long = students["name"].str.len() > 8
#
# good_data_analysis = (
#     (students["course"] == "Data Analysis") &
#     (students["final_grade"] > 65)
# )
#
# city_is_allowed = students["city"].isin(["Poznan", "Warsaw", "Gdansk"])
#
# condition = (
#     (name_is_long | good_data_analysis) &
#     city_is_allowed
# )
#
# result = students.loc[condition].sort_values("name")
#
# print(result.to_string())



# Here we create simple graph
# clients_with_discount = orders_list.loc[orders_list.country == "Poland"].groupby('region').sum()
#
# print(clients_with_discount.shape)
#
# clients_with_discount["quantity"].plot(kind="bar", figsize=(12, 7))
#
# plt.show()



# statsMoney = orders_list.profit.describe() - provides info about
# print(statsMoney) #has pd.Series type

#print(sorted(students.name.unique()))

#print(students.name.value_counts()) #how often certain name occurs

#print(students["city"].value_counts())

#print(students.groupby("course")[["assignment_score", "exam_score"]].agg(["count", "mean", "median"]))

