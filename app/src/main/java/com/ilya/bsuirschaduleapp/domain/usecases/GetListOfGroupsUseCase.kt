package com.ilya.bsuirschaduleapp.domain.usecases

import com.ilya.bsuirschaduleapp.data.network.dto.toGroup
import com.ilya.bsuirschaduleapp.domain.models.Group
import com.ilya.bsuirschaduleapp.domain.repositiries.NetworkRepository
import com.ilya.bsuirschaduleapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetListOfGroupsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    operator fun invoke(): Flow<Resource<List<Group>>> {
        return flow{
            emit(Resource.Loading())
            try {
                val data =  networkRepository.getGroupsFromApi().map { it.toGroup() }
                emit(Resource.Success(data))
            }catch (e: IOException){
                emit(Resource.Error("No connection."))
            }catch (e: HttpException){
                emit(Resource.Error("Unexpected response."))
            }

        }
    }
}