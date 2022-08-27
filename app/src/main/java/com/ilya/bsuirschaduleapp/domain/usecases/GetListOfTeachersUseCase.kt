package com.ilya.bsuirschaduleapp.domain.usecases

import com.ilya.bsuirschaduleapp.data.network.dto.toTeacher
import com.ilya.bsuirschaduleapp.domain.models.Teacher
import com.ilya.bsuirschaduleapp.domain.repositiries.NetworkRepository
import com.ilya.bsuirschaduleapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListOfTeachersUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    operator fun invoke(): Flow<Resource<List<Teacher>>> {
        return flow{
            emit(Resource.Loading())
            try {
                val data =  networkRepository.getTeachersFromApi().map { it.toTeacher() }
                emit(Resource.Success(data))
            }catch (e: IOException){
                emit(Resource.Error("No connection."))
            }catch (e: HttpException){
                emit(Resource.Error("Unexpected response."))
            }

        }
    }
}