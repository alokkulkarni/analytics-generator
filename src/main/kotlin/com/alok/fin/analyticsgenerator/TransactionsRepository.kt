package com.alok.fin.analyticsgenerator

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionsRepository: JpaRepository<Transactions, UUID>  {
}