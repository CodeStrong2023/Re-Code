import store
from fastapi import FastAPI
from fastapi.responses import HTMLResponse

app = FastAPI()


@app.get('/')
def get_list():
    return [1, 2, 3]


@app.get('/contact', response_class=HTMLResponse)
def get_list():
    return """
    <h1>hi, I am a website</h1>
    <p> hi, I am a paragraph for you to read </p>
    """


def run():
    store.get_race()


if __name__ == '__main__':
    run()
