#!/usr/bin/env bash
cd ./backend
python ./manage.py makemigrations
python ./manage.py collectstatic --noinput
gunicorn --bind 0.0.0.0:8080 kidedvisor.wsgi
