package br.com.cemobile.data.local.errors

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")

class LocalDataNotFoundException : DataSourceException("Data not found in local data source")

class ReservationException : DataSourceException("Product cannot be reserved")

class HomeDataException : DataSourceException("Home data cannot be fetched")
