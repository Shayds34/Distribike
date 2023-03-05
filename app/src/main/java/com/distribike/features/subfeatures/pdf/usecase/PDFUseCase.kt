package com.distribike.features.subfeatures.pdf.usecase

import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
import com.distribike.features.subfeatures.motorcycleform.usecase.model.MotorcycleUseCaseModel
import com.distribike.features.subfeatures.pdf.usecase.model.PDFUseCaseModel
import kotlinx.coroutines.flow.Flow

interface PDFUseCase {

    val pdfResults: Flow<PDFUseCaseModel>

    val motorcycleResults: Flow<MotorcycleUseCaseModel>

}
