#!/usr/bin/python

def parseNamespaces(path="uris.txt"):
    ns = {}
    f = open(path, "r")
    for line in f:
        #@prefix sism:       <http://purl.oclc.org/NET/sism/0.1/> .
        split = line.split(" ")
        ns[split[1][:-1]] = split[len(split)-2][1:-1]
    return ns

def readTemplate(path="template.txt"):
    data = ""
    f = open(path, "r")
    for line in f:
        data += line
    return data

def saveFile(path, data):
    f = open(path, "w")
    f.write(data)
    f.flush()
    f.close()

if __name__ == "__main__":
    ns = parseNamespaces()
    tpl = readTemplate()
    test = ""
    for k,v in ns.items():
        data = tpl
        data = data.replace("onto-name", "onto-%s" % k)
        data = data.replace("onto-test-name", "onto-test-%s" % k)
        data = data.replace("onto-uri", v)
        test += "\n<!-- %s --> \n" % k
        test += data
    saveFile("generatedtest.txt", test)

        

