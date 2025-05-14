public class QuestionData {

    public static final String[][] A1A2_QUESTIONS = {               
        {"I ... to the store yesterday.", "go", "went", "gone", "going"},//1
        {"She ... a book right now.", "read", "reads", "is reading", "reading"},//2
        {"He ... his homework every day.", "do", "does", "did", "doing"},//1
        {"I ... my keys on the table.", "leave", "leaved",   "leaving", "left"},//3
        {"She ... a letter to her friend.", "write", "writes", "wrote","writing"},//2
        {"They ... a movie last night.", "see", "saw", "seen", "seeing"},//1
        {"He ... breakfast every morning.", "make", "making", "makes", "maked"},//2
        {"We ... to the park every weekend.", "go", "went", "gone", "going"},//0
        {"She ... a cake for her birthday.", "bake", "bakes", "baked", "baking"},//2
        {"They ... a picnic last Sunday.", "have", "had", "has", "having"},//1
        {"He ... his bike to school every day.", "ride", "rides", "rode", "riding"},//1
        {"Do you know who was the first man on ... moon?", "a", "an", "the", "-"},//2
        {"Bring those ... over here, please.", "box", "boxs", "boxses", "boxes"},//3
        {"I have ... money with me, but not much.", "a little", "an", "a", "a few"},//0
        {"I enjoy ... to classical music.", "listen", "to listen", "listening", "listens"},//2
        {"How ... luggage do you have with you?", "many", "much", "few", "long"},//1
        {"Today's a beautiful day, but not ... yesterday.", "hot like", "more hotter than", "hotter like", "as hot as"},//3
        {"Who are they? I've never seen ... before.", "they", "them", "their", "themselves"},//1
        {"I asked everyone, but ... knew the answer to my question.", "anybody", "everybody", "nobody", "somebody"},//2
        {"Are you enjoying ... ?", "yourselves", "your", "yours", "you"},//0
        {" ... Fridays we finish school early.", "On", "To", "At", "In"}//0
    };

    public static final int[] A1A2_ANSWERS = {1, 2, 1, 3, 2, 1, 2, 0, 2, 1, 1, 2, 3, 0, 2, 1, 3, 1, 2, 0, 0}; //indeksy poprawnych odpowiedzi
    

    public static final String[][] B1B2_QUESTIONS = {
        {"If I ... more time,\n I would learn how to play the guitar.", "having", "had", "will have", "have"},//1
        {"It's too difficult! \n I give ... .", "away", "off", "out", "up"},//3
        {"What are you planning ... this weekend?", "to do", "doing", "do", "to doing"},//0
        {"We haven't had ... news from him yet.", "any", "no", "none", "some"},//0
        {"I'd like to get some ... details before I make a decision.", "farther", "farer", "further", "farter"},//2
        {"Please pay more attention ... what I'm saying.", "at", "in", "on", "to"},//3
        {"I've known Luke ... childhood.", "for", "from", "till", "since"},//3
        {"When I was a teenager, I ... in Berlin for 5 years.", "used to live", "lived", "was living", "had lived"},//1
        {"I ... Rome twice in my life.", "went to", "have been to", "have been in", "was in"},//1
        {"I ... in the garden all morning.", "worked", "have been worked", "have worked", "have been working"},//3
        {"Ann asked ... coffee.", "if I liked", "did I like", "if I like", "if I did like"},//0
        {"When the letter arrives, I ... you.", "inform", "will inform", "am informing", "am going to inform"},//1
        {"We ... dinner around 7 p.m. so please don't call me then.", "are having", "shall have", "will be having", "will have"},//2
        {"They went to Spain last summer, ... ?", "didn't they", "did they", "no", "yes"}, //0
        {"Tom was late and so ... .", "either was George", "was George, too", "were George", "was George"}, //3
        {"Jane, ... is the best student in the class, was given a scholarship.", "who", "that", "which", "whom"}, //0
        {"I would have come to your party if I ... so much work.", "don't have", "didn't have", "had", "hadn't had"},//1
        {"... the rain, we went camping.", "Although", "Even though", "In spite", "Despite"}, //3
        {"When we arrived, the concert ... .", "had already started", "already started", "was already started", "was already starting"},//0
        {"I heard it ... the radio.", "in", "in the", "on", "on the"}//3
    };

    public static final int[] B1B2_ANSWERS = {1, 3, 0, 0, 2, 3, 3, 1, 1, 3, 0, 1, 2, 0, 3, 0, 1, 3, 0, 3}; 


    public static final String[][] C1C2_QUESTIONS = {
        {"Despite the heavy rain,\n they decided to ... with their plans, \nknowing that it was the best opportunity they would have for months.", 
        "proceed", "persist", "postpone", "percieve"},//0
        {"Several ... for the problem have been suggested, but nothing has been proven as of yet.", 
        "hypothesis", "hypothesises", "hypotheses", "hypothese"},//2
        {"It took some time, but Dad finally came ... to my point of view.", 
        "about", "for", "in", "round"},//3
        {"He used ... to extract the splinter in his sons's finger.", 
        "pliers", "scissors", "tweezers", "tongs"},//2
        {"I ... to think what will happen when the boss finds out.", 
        "dread", "can't stand", "resist", "dislike"},//0
        {"Please accept this as a token of my gratitude ... all your help.", 
        "at", "for", "of", "to"},//1
        {"I somehow managed to stain my shirt twice today - first with wine, then with ... .", 
        "a leek", "a celery stick", "an aubergine", "a beetroot"},//3
        {"I had to read the document twice, before I could ... it all in.", 
        "bring", "make", "put", "take"},//3
        {"If you don't have any questions, I will ... to discuss the next point on the agenda.", 
        "delay", "go on", "keep", "postpone"},//1
        {"The doctors have attributed the cause of the disease ... an unknown virus.", 
        "towards", "at", "to", "for"},//2
        {"Esther is a ... successful businesswoman and is reportedly worth millions. ", 
        "highly", "extremely", "gravely", "desperately"},//0
        {"I've just got a message from the boss; we ... the report by tomorrow.", 
        "are to finish", "are about to finish", "may finish", "shall finish"},//0
        {"The phone ... for a few minutes before somebody picked up the reciever.", 
        "had rung", "had been ringing", "rang", "was ringing"},//1
        {"This food is so ... - it's completely tasteless!", 
        "bland", "sour", "savory", "starchy"},//0
        {"I hope I ... from university by the end of this year.", 
        "am going to graduate", "will have graduated", "will graduate", "will have been graduating"},//1
        {"You ... a new laptop. I had the old one fixed after all.", 
        "didn't have to buy", "didn't need to have bought", "mustn't bought", "needn't have bought"},//3
        {"They asked me why ... my previous job.", 
        "did I quit", "I quitted", "I was quitting", "I had quit"},//3
        {"Martin ... all the way through his job interview: he was so nervous.", 
        "yelled", "sighed", "stammered", "giggled"},//2
        {"The fire ... because we didn't put enough wood on it.", 
        "died out", "died off", "went off", "went away"},//0
        {"If you ... Mary, please tell her I'm looking for her.", 
        "are seeing", "happen to see", "saw", "don't see"},//1 
    };

    public static final int[] C1C2_ANSWERS = {0, 2, 3, 2, 0, 1, 3, 3, 1, 2, 0, 0, 1, 0, 1, 3, 3, 2, 0, 1};
}