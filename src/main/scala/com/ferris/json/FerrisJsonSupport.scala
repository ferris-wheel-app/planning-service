package com.ferris.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait FerrisJsonSupport extends SprayJsonSupport with DefaultJsonProtocol
