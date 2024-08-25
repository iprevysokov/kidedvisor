FROM python:3.11-slim

COPY ./backend/requirements.txt ./

RUN pip install --no-cache-dir -r ./requirements.txt && \
    pip install gunicorn==20.1.0 && \
    useradd worker && \
    mkdir /home/worker && \
    mkdir /home/worker/staticfiles

USER worker

WORKDIR /home/worker

COPY --chown=worker:worker ./backend ./backend

RUN mkdir /home/worker/backend/static && \
    chmod a+x ./backend/run.sh \