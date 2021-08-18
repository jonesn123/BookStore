package com.john.bookstore.data

import android.util.Log
import com.john.bookstore.data.local.Book
import com.john.bookstore.data.local.BooksDao
import com.john.bookstore.data.local.DetailBook
import com.john.bookstore.data.remote.WebService
import com.john.bookstore.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import  com.john.bookstore.data.dto.Result
import com.john.bookstore.data.local.SearchKeyword

class BooksRepository(
    private val booksDao: BooksDao,
    private val webService: WebService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BooksDataSource {
    override suspend fun getBooks(): Result<List<Book>> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {
            return@withContext try {
                Result.Success(webService.getNewBooks().books)
            } catch (e: Exception) {
                Result.Error(e.message)
            }

        }
    }

    override suspend fun getSearchBooks(query: String, page: Int): Result<List<Book>> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {
            return@withContext try {
                Result.Success(webService.getSearchBook(query, page).books)
            } catch (e: Exception) {
                Result.Error(e.message)
            }
        }
    }

    override suspend fun getSearchKeywords(): Result<List<SearchKeyword>> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {
            return@withContext try {
                Result.Success(booksDao.getSearchKeywords())
            } catch (e: Exception) {
                Result.Error(e.message)
            }
        }
    }

    override suspend fun insertSearchKeyword(keyword: SearchKeyword) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.insert(keyword)
                } catch (e: Exception) {
                    Log.e(TAG, "failed insertSearchKeyword : ${e.message}")
                }
            }
        }
    }

    override suspend fun deleteSearchKeyword(query: String) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.deleteSearchKeyword(query)
                } catch (e: Exception) {
                    Log.e(TAG, "failed insertSearchKeyword : ${e.message}")
                }
            }
        }
    }

    override suspend fun getBookInfo(isbn13: String): Result<DetailBook> =
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                return@withContext try {
                    Result.Success(webService.getBookInformation(isbn13))
                } catch (e: Exception) {
                    Result.Error(e.message)
                }
            }
        }

    override suspend fun getFavoriteBook(order: ORDER): Result<List<DetailBook>> =
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                return@withContext try {
                    val detailBooks = when (order) {
                        ORDER.RATING -> booksDao.getFavoriteOrderByRating()
                        ORDER.PRICE -> booksDao.getFavoriteOrderByPrice()
                        ORDER.PUBLISHED -> booksDao.getFavoriteOrderByPublished()
                    }
                    Result.Success(detailBooks)
                } catch (e: Exception) {
                    Result.Error(e.message)
                }
            }
        }

    override suspend fun getHistories(): Result<List<DetailBook>> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {
            return@withContext try {
                Result.Success(booksDao.getHistoryBooks())
            } catch (e: Exception) {
                Result.Error(e.message)
            }
        }
    }

    override suspend fun addHistory(detailBook: DetailBook) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.insert(detailBook)
                } catch (e: Exception) {
                    Log.e(TAG, "failed addHistory : ${e.message}")
                }
            }
        }
    }

    override suspend fun deleteHistory(isbn13: String) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.deleteHistory(isbn13)
                } catch (e: Exception) {
                    Log.e(TAG, "failed deleteHistory : ${e.message}")
                }
            }
        }
    }

    override suspend fun setFavorite(isLike: Boolean, isbn13: String) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.setFavorite(isLike, isbn13)
                } catch (e: Exception) {
                    Log.e(TAG, "failed setFavorite : ${e.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "BooksRepository"
    }
}
