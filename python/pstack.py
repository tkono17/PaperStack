#!/usr/bin/env python3
#-----------------------------------------------------------------------
# Paper stack application
#-----------------------------------------------------------------------

import os
import re
import urllib.request
import urllib.response
import json

hlxeListName = 'files/hlxe_list.txt'

def reqInspireList(search_string, rg=100, jrec=1):
    p = search_string
    url = 'https://inspirehep.net/search?p=%s&sf=earliestdate&rg=%d&jrec=%d' %\
                                                  (p, rg, jrec)
    response = urllib.request.urlopen(url)
    nentries = 0
    hlxe_pages = []
    if response.getcode() == 200:
        re1 = re.compile('(http.*hlxe)">LaTeX\(EU\)')
        c = str(response.read() )
        lines = c.split('\\n')
        for line in lines:
            mg = re1.search(line)
            if mg != None:
                hlxe_pages.append(mg.group(1) )
                nentries = nentries + 1
        #print('N lines: %d' % len(lines))
        #f = open('inspire.txt', 'w')
        #f.write(c)
    print('URL: %s' % url)
    print('  N entries: %d' % nentries)
    return hlxe_pages
    
def reqInspireListAll(search_string):
    rg = 100
    jrec = 1
    tryNext = True
    hlxe_pages = []
    while tryNext:
        pages = reqInspireList(search_string, rg, jrec)
        if len(pages) == rg:
            print('Try next jrec=%d' % jrec)
            tryNext = True
            jrec = jrec + rg
        else:
            tryNext = False
        hlxe_pages.extend(pages)
    print('Total number of hlxe entries: %d' % len(hlxe_pages) )
    #
    f = open('files/hlxe_list.txt', 'w')
    for p in hlxe_pages:
        f.write('%s\n' % p)
    f.close()
    pass

def getHlxeList():
    f = open(hlxeListName, 'r')
    hlxeList = []
    for line in f.readlines():
        if len(line)>0 and line[-1] == '\n':
            line = line[:-1]
        hlxeList.append(line)
    f.close()
    return hlxeList

def getInspireIdList():
    f = open(hlxeListName, 'r')
    ids = []
    re1 = re.compile('record/([\d]+/export')
    for line in f.readlines():
        if len(line)>0 and line[-1] == '\n':
            line = line[:-1]
        mg = re1.search(line)
        if mg != None:
            ids.append(mg.group(1) )
    f.close()
    return ids

def reqHlxePages():
    hlxeList = getHlxeList()
    re1 = re.compile('record/([\d]+)/export')
    re2 = re.compile('<pre>(.*)</pre>')
    
    for url in hlxeList:
        mg = re1.search(url)
        inspireId = 0
        if mg != None:
            inspireId = int(mg.group(1) )
        else:
            continue
        pageFname = 'files/inspire%d_hlxe.txt' % inspireId
        if os.path.exists(pageFname):
            continue
        response = urllib.request.urlopen(url)
        if response.getcode() == 200:
            contents = str(response.read() )
            mg = re2.search(contents)
            if mg != None:
                citation = mg.group(1)
                citation = citation.replace('\\n', '\n')
                citation = citation.replace('<br>', '\n')
                citation = citation.replace('<br/>', '\n')
                citation = citation.replace('<br />', '\n')
                f = open(pageFname, 'w')
                f.write('%s' % citation)
                f.close()
    pass

def hlxeToCitationEntry(inspireId, lines):
    # re_title = re.compile("``(.*),\\'\\'")
    re_title = re.compile("``(.*)")
    re_arxiv = re.compile('\[arXiv:(.*)\]')
    re_arxiv2 = re.compile('arXiv:(.*\])')
    re_ncite = re.compile('([\d]+) citations counted')
    re_doi = re.compile('doi:(.*)[;]*')
    #
    # format lines
    def concatLines(lines1):
        lines2 = []
        title_check = 0
        for line in lines1:
            if title_check == 0:
                if line.find('``')>=0 and line.find(r",\'\'")>=0:
                    title_check = 2
                    lines2.append(line)
                elif line.find('``')>=0 and line.find(r",\'\'")<0:
                    title_check = 1
                    lines2.append(line)
                else:
                    lines2.append(line)
            elif title_check == 1:
                #print('concat title: %s' % line)
                lines2[-1] = lines2[-1] + line
                if line.find(",\\'\\'")>=0:
                    title_check = 2
            else:
                lines2.append(line)
        return lines2
    #
    lines = concatLines(lines)
    #
    iline = 0
    (author, title, journal, arxiv, doi, ncitations) = ('', '', '', '', '', 0)
    for line in lines:
        if len(line)>0:
            line = line[:-1]
        iline = iline + 1
        #print('iline=%d line=%s' % (iline, line) )
        #
        # Interpret the line by a tag
        if len(line) == 0 or\
           (line.find('\\cite{')>=0 or line.find('\\bibitem{')>=0 or\
           line.find('CITATION = ')>=0 ):
            # comment lines
            continue
        # title
        mg = re_title.search(line)
        if mg != None:
            title = mg.group(1)
            continue
        # arXiv number
        mg = re_arxiv.search(line)
        if mg != None:
            arxiv = mg.group(1)
            continue
        else:
            mg = re_arxiv2.search(line)
            if mg != None:
                arxiv = mg.group(1)
                continue
        # DOI
        mg = re_doi.search(line)
        if mg != None:
            doi = mg.group(1)
            continue
        # N citations
        mg = re_ncite.search(line)
        if mg != None:
            ncitations = int(mg.group(1) )
            continue
        # Interpret the lines by their appearance at the line number
        if iline == 4:
            author = line.strip(' ,')
        elif iline == 5:
            title = line.strip(' ,')
        elif iline == 6:
            journal = line.strip(' ,')
        else:
            print('Unexpected line (%d) in the citation file: files/inspire%d_hlxe.txt\n  => %s' %\
                  (iline, inspireId, line) )
    entry = {
        'inspireId': inspireId, 
        'author': author,
        'title': title,
        'journal': journal,
        'arXiv': arxiv,
        'doi': doi, 
        'nCitations': ncitations
        }
    return entry

def hlxeFilesToJson():
    re1 = re.compile('inspire([\d]+)_hlxe.txt')
    #
    data = {}
    for e in os.listdir('files'):
        mg = re1.match(e)
        if mg != None:
            inspireId = int(mg.group(1) )
            print('Read files/%s' % e)
            f = open('files/%s' % e, 'r')
            entry = hlxeToCitationEntry(inspireId, f.readlines() )
            f.close()
        data['Inspire%d' % inspireId] = entry
    fout = open('files/articleData.json', 'w')
    json.dump(data, fout, indent=4)
    fout.close()
    
pass
if __name__ == '__main__':
    #reqInspireListAll('find+a+takanori+kono')
    # reqHlxePages()
    hlxeFilesToJson()
    
