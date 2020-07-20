package com.howa.mvvmsampleapp.util

import java.io.IOException

class APIExceptions(message: String) : IOException(message)

class NoInternetException(message: String) : IOException(message)