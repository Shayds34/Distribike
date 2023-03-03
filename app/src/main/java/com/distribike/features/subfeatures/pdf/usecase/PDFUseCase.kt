package com.distribike.features.subfeatures.pdf.usecase

import com.distribike.features.subfeatures.pdf.usecase.model.PDFUseCaseModel
import kotlinx.coroutines.flow.Flow

interface PDFUseCase {

    val pdfResults: Flow<PDFUseCaseModel>

}
