import json
import unicodedata

json_data = None
deputados = []


def strip_accents(text):

    try:
        text = unicode(text, 'utf-8')
    except NameError:
        pass

    text = unicodedata.normalize('NFD', text)\
        .encode('ascii', 'ignore')\
        .decode("utf-8")

    return str(text)


with open('deputados.json', encoding='utf8') as in_file:
    json_data = json.load(in_file)


for deputado in json_data['dados']:
    id_deputado = deputado['uri'].split('/')[-1]
    nome_civil = strip_accents(deputado['nomeCivil'])

    canonicalForm = id_deputado
    list_names = [nome_civil]

    deputados.append({
        'canonicalForm': canonicalForm,
        'list': list_names
    })

aux = {
    "name": "Deputadx",
    "sublists": deputados
}

with open('closedlistentitydeputadx.json', 'w', encoding='utf8') as out_file:
    json.dump(aux, out_file, ensure_ascii=False)
