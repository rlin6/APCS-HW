def cvs2html(filename,newfile):
    straw = open(filename,'rU')
    suck = straw.read()
    lines = suck.split('\n')
    heading = []
    html = '<html><body><center><h1>' + filename + '</h1>\n<table border = 1>\n<tr>'
    for i in lines:
        heading.append(i.split(','))
    for i in heading[0]:
        html += '<td><b>' + i + '</b></td>'
    html += '</tr>'
    for line in lines[1:-1]:
        grades = line.split(',')
        html += '<tr>'
        for i in grades:
            html += '<td>' + i + '</td>'
        html += '</tr>\n'
    html += '</table></center></body></html>'
    new = open(newfile,'w')
    new.write(html)
    new.close()
    return html

print cvs2html('grades.csv','csv2html.html')
