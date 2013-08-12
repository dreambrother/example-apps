from django.http import HttpResponse
from django.shortcuts import render, redirect
from django_example.models import Book


def test(request):
    return HttpResponse("Django works")


def books(request):
    return render(request, "books.html", {'books': Book.objects.all()})


def create_book(request):
    return render(request, "create-book.html")


def save_book(request):
    Book(title=request.POST["title"], author=request.POST["author"], price=request.POST["price"]).save()
    return redirect("books.html")
