package com.example.studenthub_compose.domain.usecase

import com.example.studenthub_compose.domain.model.User
import com.example.studenthub_compose.domain.repository.UserRepository

/**
 * UseCase for registering a new user.
 *
 * @param repo UserRepository instance for data operations
 */
class RegisterUserUseCase(
    private val repo: UserRepository
){
    /**
     * Registers the given user if username is not already taken.
     *
     * @param user The User domain model to register
     */
    suspend operator fun invoke(user: User){
        repo.insertUser(user)
    }
}