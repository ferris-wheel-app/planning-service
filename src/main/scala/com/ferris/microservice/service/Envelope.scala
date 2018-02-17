package com.ferris.microservice.service

case class Envelope[T](status: String, data: T)
