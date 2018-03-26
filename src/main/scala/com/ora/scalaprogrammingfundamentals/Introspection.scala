package com.ora.scalaprogrammingfundamentals

trait Introspection{
  def whoAmI_?() = s"${getClass.getSimpleName}"
}