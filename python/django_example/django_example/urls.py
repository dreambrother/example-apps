from django.conf.urls import patterns, url
import views

urlpatterns = patterns('',
    url(r'test', views.test),
    url(r'books', views.books),
    url(r'create-book', views.create_book),
    url(r'save-book', views.save_book, name='save_book')
)
