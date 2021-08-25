Custom Application of Udacity final project.
This application is about my BookStore.

<h2> Functions </h2>
<div>

- OverView of Project

1. There are five screen, [BookMark], [RECENT PUBLISHED], [SEARCH], [HISTORY], [DETAIL OF BOOK]
2. Firstpage is bookmark that the list of liked books.
3. If click item of list, we need to show [DETAIL OF BOOK] pages.
4. Using MVVM pattern with Android Jetpack
5. API reference : https://api.itbook.store/1.0

- Specification of each Pages.

1. [BOOKMARK] Page.

- This page is saved books list.
- If you liked book in detail page of book. We can show in this list.
- It can ordering according to rating, publised, price.

2. [DETAIL OF BOOK] Page.

- Show all informations of selected book.
- We can get the informations in API(https://api.itbook.store/1.0/books/{isbn13})
- Users can write memo about book. 

3. [RECENT PUBLISHED] Page.

- Show all informations of recent published book.
- Using scrolled list to show recent published.
- Api is https://api.itbook.store/1.0/new

4. [SEARCH] Page.

- We can search using keyword of books.
- There are many pages of search data.
- Show all search datas scrolled list.
- Using api https://api.itbook.store/1.0/search/{query}, https://api.itbook.store/1.0/search/{query}/{page}

5. [HISTORY] Page

- For users, this page offer book list that users show detail page of book.


<h2> Prepare a design document <h2>
<div>




<h2> project milestone </h2>
<div>

https://github.com/jonesn123/BookStore/milestones?with_issues=no
