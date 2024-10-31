import requests


def get_race():
    response = requests.get('https://dog.ceo/api/breeds/list/all')
    print(response.status_code)
    dic = dict(response.json())
    print(dic)
    for key, value in dic['message'].items():
        print(f'dog race: {key}')
