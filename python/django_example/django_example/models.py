from django.db import models

__author__ = 'nik'


class Book(models.Model):
    title = models.CharField(max_length=30)
    author = models.CharField(max_length=30)
    price = models.IntegerField()
