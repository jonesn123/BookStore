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
import com.john.bookstore.data.remote.SearchBooksResponse

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

    override suspend fun getSearchBooks(query: String, page: Int): Result<SearchBooksResponse> = withContext(ioDispatcher) {
        wrapEspressoIdlingResource {
            return@withContext try {
                Result.Success(webService.getSearchBook(query, page))
            } catch (e: Exception) {
                Result.Error(e.message)
            }
        }
    }

    override suspend fun getBookInfo(isbn13: String): Result<DetailBook> =
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                var detailBook = booksDao.getDetailBook(isbn13)
                return@withContext if (detailBook != null) {
                    Result.Success(detailBook)
                } else {
                    try {
                        detailBook = webService.getBookInformation(isbn13)
                        addHistory(detailBook)
                        Result.Success(detailBook)
                    } catch (e: Exception) {
                        Result.Error(e.message)
                    }
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

    override suspend fun deleteAllHistory() {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.deleteAllHistory()
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

    override suspend fun saveMemo(memo: String, isbn13: String) {
        withContext(ioDispatcher) {
            wrapEspressoIdlingResource {
                try {
                    booksDao.setMemo(memo, isbn13)
                } catch (e: Exception) {
                    Log.e(TAG, "failed setMemo : ${e.message}")
                }
            }
        }
    }

    companion object {
        private const val TAG = "BooksRepository"
    }
}
