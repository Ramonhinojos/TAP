# definir todos los token y enumerarlos
import enum
class tokentype(enum.Enum):
    EOF=1
    NEWLINE=0
    NUMBER=1
    IDENT=2
    STRING=3
    ##KEYWORDS
    LABEL=101
    GOTO=102
    PRINT=103
    INPUT=104
    LET=105
    IF=106
    THEN=107
    ENDIF=108
    WHILE=109
    REPEAT=100
    ENDWHILE=111
    ACCTION =120
    BUSCAR =121
    ELIMINAR =122
    AGREGAR=123
    EDITAR=124
    INSERTAR=125
    NAME=130
    DESCRIPCIÓN=131
    FOTOGRAFÍA=132
    APODO=133
    PÁGINA=134
    PREPOSITION=140
    DE=141
    LA=142
    DEL=143
    AL=144
    PARA=145
    ANIMAL= 150
    HIENA=151
    ELEFANTE=152
    PULPO=153
    GATO=154
    ARDILLA=155
    JAGUAR=156
    PERRO=157
    ORCA=158
    COLIBRÍ=159
    PANDA=160

    #OPERADORES
    EQ=201
    PLUS=202
    MINUS=203
    ASTERISK=204
    SLASH=205
    EQEQ=206
    NOTEQ=207
    LT=208
    LTEQ=209
    GT=210
    GTEQ=211
    