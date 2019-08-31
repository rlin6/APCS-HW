#Team ____ Dennis Chen, Ricky Lin
#SoftDev pd6
#K10: Jinja Tuning ...
#2018-09-24
import random
from flask import Flask, render_template
#from _____ import _______

app = Flask(__name__)

@app.route("/")
def hello_World():
    print(__name__) #terminal output
    return "<a href='/foo'> No hablo queso! </a>"

@app.route("/foo")
def cheez_it():
    return "<a href='/boo'> Cheese is Supreme </a>"

@app.route("/boo")
def lactose():
    return "<a href='/yoo'> Cheese & Crackers </a>"

@app.route("/yoo")
def cheesy():
    return "<a href='/'> Chuck E Cheese </a>"

def dictFile(filename):
    try:
        # tries to open the file name
        f= open(filename,'r')
    except:
        # if it cannot open it
        print("File does not exist")
        return 0

    fText = f.read() # readable text
    lines = fText.split('\n')[1:] # list of each row

    lines = lines[:len(lines)-1]
    allOcc = [] # list of all occupations with percentages
    links = []
    for each in lines:
        # if there is a double quote
        if "\"" in each:
            # split on double quotes
            # occ[0] is the occupation
            # occ[1] is the percentage
            occ = each.split("\"")[1:]
            # turns the percentage of occupation into a float
            occ[1] =(occ[1][1:])
            # splits list into occ, percent, and link
            occ[1] = occ[1].split(",")
            percent = occ[1][0]
            link = occ[1][1]
            occ[1] = float(percent)
            links.append(link)

        else:
            # regularly split on comma
            occ = each.split(',')
            links.append(occ.pop())
            # turns the percentage of occupation into a float
            occ[1] = float(occ[1])

        allOcc.append(occ)
    return dict(allOcc), links # create the dictionary of occupations matched with their percentages

def occChooser():
    occDict, links = dictFile("occupations.csv") # dictionary of all occupations with percentages
    if occDict == 0:
        return;

    '''
    Algorithm

    Sample:
    1st occupation = teacher --> 20%
    2nd occupation = doctor --> 30%
    3rd occupation = farmer --> 50%

    Pointer randomly chosen lands at 40.
    --> The 2nd occupation, doctor, is chosen.

      1st            2nd                3rd
    ==========|==============|========================|
    |----|----|----|----|----|----|----|----|----|----|
      10   20   30   40   50   60   70   80   90   100
                     ^
                     |
                 (pointer)
    '''

    summ = 0 # collects the sum of the percentages throughout the algorithm
    index = 0;
    pointer = random.randint(1,998) # out of total = 98.0
    for key in occDict:
        summ += occDict[key] * 10
        if pointer < summ:
            return key, index
        index += 1


@app.route("/occupations")
def occupations():
    occDict, links = dictFile("occupations.csv")
    occChosen, index = occChooser()
    i = 0
    for key in occDict:
        occDict[key] = [occDict[key],links[i]]
        i += 1
    return render_template('teamName_chenD-lastR.html',random = occChosen,link = links[index], _dict = occDict)

if __name__ == '__main__':
    app.run(debug=True)
