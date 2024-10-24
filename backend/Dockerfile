FROM python:3.11-slim

COPY ./requirements.txt ./

RUN pip install --no-cache-dir -r ./requirements.txt
RUN pip install gunicorn==20.1.0

RUN useradd worker

RUN mkdir /home/worker
RUN mkdir /home/worker/staticfiles

USER worker

WORKDIR /home/worker

COPY --chown=worker:worker ./ ./backend
RUN python ./backend/manage.py makemigrations
RUN python ./backend/manage.py migrate
RUN chmod a+x ./backend/run.sh