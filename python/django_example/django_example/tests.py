from django.test.testcases import TestCase
from django_example.models import Book

__author__ = 'nik'


class BookTest(TestCase):

    def test_create_book(self):
        book = Book(title="Test", author="Author", price=123)
        book.save()
        self.assertEqual(Book.objects.all()[0], book)