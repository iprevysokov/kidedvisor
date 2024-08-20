FROM python:3.11-slim

ENV DEBUG=False\
    ALLOWED_HOSTS='0.0.0.0'

COPY ./backend/requirements.txt ./

RUN pip install --no-cache-dir -r ./requirements.txt

RUN useradd worker

USER worker

WORKDIR /home/worker

COPY --chown=worker:worker ./backend ./backend