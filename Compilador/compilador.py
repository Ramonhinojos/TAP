import token
from lex import *
from tokentype import *

def main():
    #input= "IF+-123123 algo*THEN"
    file = open("./digital.any","r")
    input = file.read()

    lexer=Lexer(input)
    token=lexer.getToken()
    cont=0
    while token.kind != TokenType.EOF:
        #print(token.kind)
        print("Token Type: {} , Content: {}".format(token.kind, token.text))
        token=lexer.getToken()
        cont+=1
    print("Number tokensfound: {}".format(count))
main()