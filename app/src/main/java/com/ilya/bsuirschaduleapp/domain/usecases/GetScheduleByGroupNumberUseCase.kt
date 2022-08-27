package com.ilya.bsuirschaduleapp.domain.usecases

import com.ilya.bsuirschaduleapp.data.network.dto.toScheduleResponse
import com.ilya.bsuirschaduleapp.domain.models.ScheduleResponse
import com.ilya.bsuirschaduleapp.domain.repositiries.NetworkRepository
import com.ilya.bsuirschaduleapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetScheduleByGroupNumberUseCase @Inject constructor (
    private val networkRepository: NetworkRepository
    ) {

    operator fun invoke(groupNumber: String):Flow<Resource<ScheduleResponse>>{
        return flow{
            emit(Resource.Loading())
            try {
               val data =  networkRepository.getScheduleByGroupNumber(groupNumber).toScheduleResponse()
                emit(Resource.Success(data))
            }catch (e: IOException){
                emit(Resource.Error("No connection."))
            }catch (e: HttpException){
                emit(Resource.Error("Unexpected response."))
            }

        }
    }
}