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


<h2> Prepare a design document </h2>
<div>

<img width="841" alt="스크린샷 2021-08-25 오전 9 20 23" src="https://user-images.githubusercontent.com/8240503/130706683-35af6985-f884-4e6a-bbb4-e3b40b3afe87.png" />

  
  
<img src="https://user-images.githubusercontent.com/8240503/130706712-615350f7-e97e-41ce-a792-acbeb9140395.png" width="350" height="700" />

  
  
<img src="https://user-images.githubusercontent.com/8240503/130706750-94c94615-0f4e-4859-a13a-493d7a975c07.png" width="350" height="700" />

  
<img src="https://user-images.githubusercontent.com/8240503/130706785-5c4cf9b1-ee7b-4f13-9d9e-4b7b15fb9a30.png" width="350" height="700" />

<img src="https://user-images.githubusercontent.com/8240503/130706912-5823d737-275a-4a2e-8e7d-c1bff6a84426.png" width="350" height="700" />


  

<h2> project milestone </h2>

  <div>

  - https://github.com/jonesn123/BookStore/milestones?with_issues=no
  
  
  

<h2> Requirement (CRITERIA) </h2>
<div>
  
  <h3>Android UI/UX</h3>
  
  - Build a navigable interface consisting of multiple screens of functionality and data.
  
  There are 4 screens with Android Navigation Controller, When goes to DetailBookFragment it needs to bundle data.
  
  - Construct interfaces that adhere to Android standards and display appropriately on screens of different size and resolution.
  
  Consistant with ConstraintLayout and using resource directory, in case of data collections used RecyclerView.
  
  - Animate UI components to better utilize screen real estate and create engaging content.
  
  When user click like button in DetailBookFragment page, MotionLayout heavior is defiend.
 
  <h3>Local and Network data</h3>
  
  - Connect to and consume data from a remote data source such as a RESTful API.
  
  BookStore Applications connects to Restfull api https://api.itbook.store/1.0
  
  Using helper libraries Moshi, and converted data url to link, because it confused with image url.
  
  - Load network resources, such as Bitmap Images, dynamically and on-demand.
  
  Book images are dispayed in list and detail screen. 
  
  When loading image, display placeholder.
  
  All request are performed aynchronously using coroutine.
  
  - Store data locally on the device for use between application sessions and/or offline use.
  
  BookStore Application used Room for persistence data. And application can oprate with out network connection.
  
  <h3>Android system and hardware integration</h3>
  
  - Architect application functionality using MVVM.
  
  BookStore appliacation's architecture applied MVVM pattern using Jetpack. 
  
  - Implement logic to handle and respond to hardware and system events that impact the Android Lifecycle.
  
  Data are sotored in ViewModel class, so we can obtain when we can handle and respond to hardware and system event that impact the Lifecycle.
  
  - Utilize system hardware to provide the user with advanced functionality and features.
  
  BookStore application used Notifications when the user clicked like button, notiticaion show about book's title and subtitle. 
  
  And we click the notification item, we can go book's detail pages
