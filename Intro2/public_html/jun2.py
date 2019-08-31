#! /usr/bin/python

import cgi, cgitb
cgitb.enable()

top_header='Content-type: text/html\n\n'

def main():
    form=cgi.FieldStorage()
    print top_header
    print '<html><body>' 
    the_name=form.getvalue('fnork','')
    print 'You glorious name is:' + the_name
    print '<br>' 
    if form.getvalue('ego','') == 'inflated':
        print 'Your ego is vastly inflated.'
    else:
        print 'You have a realistic view.'
    print '</body></html>'

main()
