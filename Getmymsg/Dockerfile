FROM python:3.8
RUN pip install git+https://gitlab.com/ucab-ds/getmymsg.git
EXPOSE 19876
WORKDIR /app
COPY getmymsg.yaml /app
CMD ["getmymsg-server"]