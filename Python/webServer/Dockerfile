FROM python:3.10
WORKDIR /app
COPY requirements.txt /app/requeriments.txt
RUN pip3 install --no-cache-dir --upgrade -r /app/requeriments.txt
COPY . /app
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]