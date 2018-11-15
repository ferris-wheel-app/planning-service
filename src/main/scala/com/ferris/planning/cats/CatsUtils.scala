package com.ferris.planning.cats

import cats.data.EitherT
import com.ferris.planning.service.exceptions.Exceptions.PlanningServiceException

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object CatsUtils {
  def fromFuture[T](futureResult: Future[T]): EitherT[Future, PlanningServiceException, T] =
    EitherT(futureResult.map(Right(_): Either[PlanningServiceException, T]).recover {
      case exception: PlanningServiceException => Left(exception)
    })
}
