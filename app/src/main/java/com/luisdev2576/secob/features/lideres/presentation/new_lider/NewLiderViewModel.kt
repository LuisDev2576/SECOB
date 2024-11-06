package com.luisdev2576.secob.features.lideres.presentation.new_lider

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.luisdev2576.secob.features.lideres.domain.Contacto
import com.luisdev2576.secob.features.lideres.domain.DatosPersonales
import com.luisdev2576.secob.features.lideres.domain.DocumentoIdentidad
import com.luisdev2576.secob.features.lideres.domain.Domicilio
import com.luisdev2576.secob.features.lideres.domain.Fecha
import com.luisdev2576.secob.features.lideres.domain.LiderInformation
import com.luisdev2576.secob.features.lideres.domain.NombreCompleto
import com.luisdev2576.secob.features.lideres.domain.Notificacion
import com.luisdev2576.secob.features.lideres.domain.Organizacion
import com.luisdev2576.secob.features.lideres.domain.TerceroDeContactoInformation
import com.luisdev2576.secob.features.lideres.domain.Ubicacion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class NewLiderViewModel @Inject constructor() : ViewModel() {

    // Estado para LiderInformation
    val liderId = mutableStateOf("")

    // Estado para NombreCompleto
    val primerNombre = mutableStateOf("")
    val segundoNombre = mutableStateOf("")
    val apellidoPaterno = mutableStateOf("")
    val apellidoMaterno = mutableStateOf("")
    val nombreIdentitario = mutableStateOf("")

    // Estado para DocumentoIdentidad
    val tipoDocumento = mutableStateOf("")
    val numeroDocumento = mutableStateOf("")
    val fechaExpedicionDia = mutableStateOf(1)
    val fechaExpedicionMes = mutableStateOf(1)
    val fechaExpedicionAno = mutableStateOf(2000)

    // Estado para Ubicacion (Lugar de Nacimiento)
    val paisNacimiento = mutableStateOf("")
    val departamentoNacimiento = mutableStateOf("")
    val ciudadNacimiento = mutableStateOf("")

    // Estado para Domicilio (Residencia)
    val paisResidencia = mutableStateOf("")
    val departamentoResidencia = mutableStateOf("")
    val ciudadResidencia = mutableStateOf("")
    val corregimiento = mutableStateOf("")
    val vereda = mutableStateOf("")
    val barrio = mutableStateOf("")
    val zona = mutableStateOf("") // Rural o Urbana
    val direccion = mutableStateOf("")
    val indicaciones = mutableStateOf("")

    // Estado para Contacto
    val telefonoCelular1 = mutableStateOf("")
    val telefonoCelular2 = mutableStateOf("")
    val telefonoFijo = mutableStateOf("")
    val correoElectronico = mutableStateOf("")

    // Estado para Notificacion
    val autorizaNotificacionesCorreo = mutableStateOf(false)
    val paisNotificacion = mutableStateOf("")
    val departamentoNotificacion = mutableStateOf("")
    val ciudadNotificacion = mutableStateOf("")
    val direccionNotificacion = mutableStateOf("")

    // Estado para TerceroDeContactoInformation (opcional)
    val terceroNombresApellidos = mutableStateOf("")
    val terceroPaisResidencia = mutableStateOf("")
    val terceroDepartamentoResidencia = mutableStateOf("")
    val terceroCiudadResidencia = mutableStateOf("")
    val terceroCorregimiento = mutableStateOf("")
    val terceroVereda = mutableStateOf("")
    val terceroBarrio = mutableStateOf("")
    val terceroZona = mutableStateOf("") // Rural o Urbana
    val terceroDireccion = mutableStateOf("")
    val terceroIndicaciones = mutableStateOf("")
    val terceroTelefonoCelular1 = mutableStateOf("")
    val terceroTelefonoCelular2 = mutableStateOf("")
    val terceroTelefonoFijo = mutableStateOf("")
    val terceroCorreoElectronico = mutableStateOf("")
    val terceroAutorizaNotificacionesCorreo = mutableStateOf(false)

    // Estado para DatosPersonales
    val sexo = mutableStateOf("") // Hombre, Mujer, Intersexual
    val genero = mutableStateOf("") // Femenino, Masculino, Transgénero
    val orientacionSexual = mutableStateOf("") // Heterosexual, Homosexual, Bisexual
    val grupoEtario = mutableStateOf("") // Niña/Niño, Adolescente, Joven, Adulto, Adulto Mayor
    val otroFactorDiferencial = mutableStateOf("") // Ej. Madre Cabeza de Familia, Cuidador
    val personasACargo = mutableStateOf<Int?>(null)
    val poseeDiscapacidad = mutableStateOf(false)
    val tipoDeDiscapacidad = mutableStateOf("") // Física, Auditiva, Visual, etc.
    val grupoEtnico = mutableStateOf("") // Indígena, Afrocolombiano, Palenquero, etc.

    // Estado para Organizacion (opcional)
    val tipoOrganizacion = mutableStateOf("") // Social, Gremial, Campesina, etc.
    val nombreOrganizacion = mutableStateOf("")
    val personaJuridicaOrganizacion = mutableStateOf(false)

    // Estado para MedidaCautelar (opcional)
    val medidaCautelar = mutableStateOf("")

    // Funciones para actualizar los campos

    // Funciones para LiderInformation
    fun onLiderIdChange(newId: String) {
        liderId.value = newId
    }

    // Funciones para NombreCompleto
    fun onPrimerNombreChange(newName: String) {
        primerNombre.value = newName
    }

    fun onSegundoNombreChange(newName: String?) {
        segundoNombre.value = newName ?: ""
    }

    fun onApellidoPaternoChange(newApellido: String) {
        apellidoPaterno.value = newApellido
    }

    fun onApellidoMaternoChange(newApellido: String?) {
        apellidoMaterno.value = newApellido ?: ""
    }

    fun onNombreIdentitarioChange(newNombre: String?) {
        nombreIdentitario.value = newNombre ?: ""
    }

    // Funciones para DocumentoIdentidad
    fun onTipoDocumentoChange(newTipo: String) {
        tipoDocumento.value = newTipo
    }

    fun onNumeroDocumentoChange(newNumero: String) {
        numeroDocumento.value = newNumero
    }

    fun onFechaExpedicionDiaChange(newDia: Int) {
        fechaExpedicionDia.value = newDia
    }

    fun onFechaExpedicionMesChange(newMes: Int) {
        fechaExpedicionMes.value = newMes
    }

    fun onFechaExpedicionAnoChange(newAno: Int) {
        fechaExpedicionAno.value = newAno
    }

    // Funciones para Ubicacion (Lugar de Nacimiento)
    fun onPaisNacimientoChange(newPais: String) {
        paisNacimiento.value = newPais
    }

    fun onDepartamentoNacimientoChange(newDepartamento: String) {
        departamentoNacimiento.value = newDepartamento
    }

    fun onCiudadNacimientoChange(newCiudad: String) {
        ciudadNacimiento.value = newCiudad
    }

    // Funciones para Domicilio (Residencia)
    fun onPaisResidenciaChange(newPais: String) {
        paisResidencia.value = newPais
    }

    fun onDepartamentoResidenciaChange(newDepartamento: String) {
        departamentoResidencia.value = newDepartamento
    }

    fun onCiudadResidenciaChange(newCiudad: String) {
        ciudadResidencia.value = newCiudad
    }

    fun onCorregimientoChange(newCorregimiento: String?) {
        corregimiento.value = newCorregimiento ?: ""
    }

    fun onVeredaChange(newVereda: String?) {
        vereda.value = newVereda ?: ""
    }

    fun onBarrioChange(newBarrio: String?) {
        barrio.value = newBarrio ?: ""
    }

    fun onZonaChange(newZona: String) {
        zona.value = newZona
    }

    fun onDireccionChange(newDireccion: String) {
        direccion.value = newDireccion
    }

    fun onIndicacionesChange(newIndicaciones: String?) {
        indicaciones.value = newIndicaciones ?: ""
    }

    // Funciones para Contacto
    fun onTelefonoCelular1Change(newTelefono: String) {
        telefonoCelular1.value = newTelefono
    }

    fun onTelefonoCelular2Change(newTelefono: String?) {
        telefonoCelular2.value = newTelefono ?: ""
    }

    fun onTelefonoFijoChange(newTelefono: String?) {
        telefonoFijo.value = newTelefono ?: ""
    }

    fun onCorreoElectronicoChange(newCorreo: String) {
        correoElectronico.value = newCorreo
    }

    // Funciones para Notificacion
    fun onAutorizaNotificacionesCorreoChange(newValue: Boolean) {
        autorizaNotificacionesCorreo.value = newValue
    }

    fun onPaisNotificacionChange(newPais: String) {
        paisNotificacion.value = newPais
    }

    fun onDepartamentoNotificacionChange(newDepartamento: String) {
        departamentoNotificacion.value = newDepartamento
    }

    fun onCiudadNotificacionChange(newCiudad: String) {
        ciudadNotificacion.value = newCiudad
    }

    fun onDireccionNotificacionChange(newDireccion: String) {
        direccionNotificacion.value = newDireccion
    }

    // Funciones para TerceroDeContactoInformation (opcional)
    fun onTerceroNombresApellidosChange(newNombres: String) {
        terceroNombresApellidos.value = newNombres
    }

    fun onTerceroPaisResidenciaChange(newPais: String) {
        terceroPaisResidencia.value = newPais
    }

    fun onTerceroDepartamentoResidenciaChange(newDepartamento: String) {
        terceroDepartamentoResidencia.value = newDepartamento
    }

    fun onTerceroCiudadResidenciaChange(newCiudad: String) {
        terceroCiudadResidencia.value = newCiudad
    }

    fun onTerceroCorregimientoChange(newCorregimiento: String?) {
        terceroCorregimiento.value = newCorregimiento ?: ""
    }

    fun onTerceroVeredaChange(newVereda: String?) {
        terceroVereda.value = newVereda ?: ""
    }

    fun onTerceroBarrioChange(newBarrio: String?) {
        terceroBarrio.value = newBarrio ?: ""
    }

    fun onTerceroZonaChange(newZona: String) {
        terceroZona.value = newZona
    }

    fun onTerceroDireccionChange(newDireccion: String) {
        terceroDireccion.value = newDireccion
    }

    fun onTerceroIndicacionesChange(newIndicaciones: String?) {
        terceroIndicaciones.value = newIndicaciones ?: ""
    }

    fun onTerceroTelefonoCelular1Change(newTelefono: String) {
        terceroTelefonoCelular1.value = newTelefono
    }

    fun onTerceroTelefonoCelular2Change(newTelefono: String?) {
        terceroTelefonoCelular2.value = newTelefono ?: ""
    }

    fun onTerceroTelefonoFijoChange(newTelefono: String?) {
        terceroTelefonoFijo.value = newTelefono ?: ""
    }

    fun onTerceroCorreoElectronicoChange(newCorreo: String) {
        terceroCorreoElectronico.value = newCorreo
    }

    fun onTerceroAutorizaNotificacionesCorreoChange(newValue: Boolean) {
        terceroAutorizaNotificacionesCorreo.value = newValue
    }

    // Funciones para DatosPersonales
    fun onSexoChange(newSexo: String) {
        sexo.value = newSexo
    }

    fun onGeneroChange(newGenero: String) {
        genero.value = newGenero
    }

    fun onOrientacionSexualChange(newOrientacion: String?) {
        orientacionSexual.value = newOrientacion ?: ""
    }

    fun onGrupoEtarioChange(newGrupo: String) {
        grupoEtario.value = newGrupo
    }

    fun onOtroFactorDiferencialChange(newFactor: String?) {
        otroFactorDiferencial.value = newFactor ?: ""
    }

    fun onPersonasACargoChange(newPersonas: Int?) {
        personasACargo.value = newPersonas
    }

    fun onPoseeDiscapacidadChange(newValue: Boolean) {
        poseeDiscapacidad.value = newValue
    }

    fun onTipoDeDiscapacidadChange(newTipo: String?) {
        tipoDeDiscapacidad.value = newTipo ?: ""
    }

    fun onGrupoEtnicoChange(newGrupoEtnico: String?) {
        grupoEtnico.value = newGrupoEtnico ?: ""
    }

    // Funciones para Organizacion (opcional)
    fun onTipoOrganizacionChange(newTipo: String?) {
        tipoOrganizacion.value = newTipo ?: ""
    }

    fun onNombreOrganizacionChange(newNombre: String?) {
        nombreOrganizacion.value = newNombre ?: ""
    }

    fun onPersonaJuridicaOrganizacionChange(newValue: Boolean) {
        personaJuridicaOrganizacion.value = newValue
    }

    // Funciones para MedidaCautelar (opcional)
    fun onMedidaCautelarChange(newMedida: String?) {
        medidaCautelar.value = newMedida ?: ""
    }

    // Función para construir el objeto LiderInformation
    fun buildLiderInformation(): LiderInformation {
        val nombreCompleto = NombreCompleto(
            primerNombre = primerNombre.value,
            segundoNombre = if (segundoNombre.value.isNotBlank()) segundoNombre.value else null,
            apellidoPaterno = apellidoPaterno.value,
            apellidoMaterno = if (apellidoMaterno.value.isNotBlank()) apellidoMaterno.value else null,
            nombreIdentitario = if (nombreIdentitario.value.isNotBlank()) nombreIdentitario.value else null
        )

        val fechaExpedicion = Fecha(
            dia = fechaExpedicionDia.value,
            mes = fechaExpedicionMes.value,
            ano = fechaExpedicionAno.value
        )

        val documentoIdentidad = DocumentoIdentidad(
            tipoDocumento = tipoDocumento.value,
            numeroDocumento = numeroDocumento.value,
            fechaExpedicion = fechaExpedicion
        )

        val lugarNacimiento = Ubicacion(
            pais = paisNacimiento.value,
            departamento = departamentoNacimiento.value,
            ciudad = ciudadNacimiento.value
        )

        val ubicacionResidencia = Ubicacion(
            pais = paisResidencia.value,
            departamento = departamentoResidencia.value,
            ciudad = ciudadResidencia.value
        )

        val domicilio = Domicilio(
            ubicacion = ubicacionResidencia,
            corregimiento = if (corregimiento.value.isNotBlank()) corregimiento.value else null,
            vereda = if (vereda.value.isNotBlank()) vereda.value else null,
            barrio = if (barrio.value.isNotBlank()) barrio.value else null,
            zona = zona.value,
            direccion = direccion.value,
            indicaciones = if (indicaciones.value.isNotBlank()) indicaciones.value else null
        )

        val contactoInfo = Contacto(
            telefonoCelular1 = telefonoCelular1.value,
            telefonoCelular2 = if (telefonoCelular2.value.isNotBlank()) telefonoCelular2.value else null,
            telefonoFijo = if (telefonoFijo.value.isNotBlank()) telefonoFijo.value else null,
            correoElectronico = correoElectronico.value
        )

        val ubicacionNotificacion = Ubicacion(
            pais = paisNotificacion.value,
            departamento = departamentoNotificacion.value,
            ciudad = ciudadNotificacion.value
        )

        val notificacionInfo = Notificacion(
            autorizaNotificacionesCorreo = autorizaNotificacionesCorreo.value,
            ubicacionNotificacion = ubicacionNotificacion,
            direccionNotificacion = direccionNotificacion.value
        )

        val terceroDeContactoInfo = if (terceroNombresApellidos.value.isNotBlank()) {
            val terceroUbicacion = Ubicacion(
                pais = terceroPaisResidencia.value,
                departamento = terceroDepartamentoResidencia.value,
                ciudad = terceroCiudadResidencia.value
            )

            val terceroDomicilio = Domicilio(
                ubicacion = terceroUbicacion,
                corregimiento = if (terceroCorregimiento.value.isNotBlank()) terceroCorregimiento.value else null,
                vereda = if (terceroVereda.value.isNotBlank()) terceroVereda.value else null,
                barrio = if (terceroBarrio.value.isNotBlank()) terceroBarrio.value else null,
                zona = terceroZona.value,
                direccion = terceroDireccion.value,
                indicaciones = if (terceroIndicaciones.value.isNotBlank()) terceroIndicaciones.value else null
            )

            val terceroContacto = Contacto(
                telefonoCelular1 = terceroTelefonoCelular1.value,
                telefonoCelular2 = if (terceroTelefonoCelular2.value.isNotBlank()) terceroTelefonoCelular2.value else null,
                telefonoFijo = if (terceroTelefonoFijo.value.isNotBlank()) terceroTelefonoFijo.value else null,
                correoElectronico = terceroCorreoElectronico.value
            )

            TerceroDeContactoInformation(
                nombresApellidos = terceroNombresApellidos.value,
                domicilio = terceroDomicilio,
                contacto = terceroContacto,
                autorizaNotificacionesCorreo = terceroAutorizaNotificacionesCorreo.value
            )
        } else {
            null
        }

        val datosPersonalesInfo = DatosPersonales(
            sexo = sexo.value,
            genero = genero.value,
            orientacionSexual = if (orientacionSexual.value.isNotBlank()) orientacionSexual.value else null,
            grupoEtario = grupoEtario.value,
            otroFactorDiferencial = if (otroFactorDiferencial.value.isNotBlank()) otroFactorDiferencial.value else null,
            personasACargo = personasACargo.value,
            poseeDiscapacidad = poseeDiscapacidad.value,
            tipoDeDiscapacidad = if (tipoDeDiscapacidad.value.isNotBlank()) tipoDeDiscapacidad.value else null,
            grupoEtnico = if (grupoEtnico.value.isNotBlank()) grupoEtnico.value else null
        )

        val organizacionInfo = if (tipoOrganizacion.value.isNotBlank() || nombreOrganizacion.value.isNotBlank()) {
            Organizacion(
                tipoOrganizacion = if (tipoOrganizacion.value.isNotBlank()) tipoOrganizacion.value else null,
                nombreOrganizacion = if (nombreOrganizacion.value.isNotBlank()) nombreOrganizacion.value else null,
                personaJuridicaOrganizacion = personaJuridicaOrganizacion.value
            )
        } else {
            null
        }

        return LiderInformation(
            liderId = liderId.value,
            nombreCompleto = nombreCompleto,
            documentoIdentidad = documentoIdentidad,
            fechaNacimiento = Fecha(
                dia = fechaExpedicionDia.value, // Asumiendo que fechaNacimiento es igual a fechaExpedicion
                mes = fechaExpedicionMes.value,
                ano = fechaExpedicionAno.value
            ),
            lugarNacimiento = lugarNacimiento,
            residencia = domicilio,
            contacto = contactoInfo,
            notificacion = notificacionInfo,
            informacionDeContacto = terceroDeContactoInfo,
            datosPersonales = datosPersonalesInfo,
            organizacion = organizacionInfo,
            medidaCautelar = if (medidaCautelar.value.isNotBlank()) medidaCautelar.value else null
        )
    }

    private val requiredFields = listOf(
        // Nombre Completo
        { primerNombre.value.isNotBlank() },
        { apellidoPaterno.value.isNotBlank() },
        { apellidoMaterno.value.isNotBlank() },
        // Documento Identidad
        { tipoDocumento.value.isNotBlank() },
        { numeroDocumento.value.isNotBlank() },
        // Ubicación de Nacimiento
        { paisNacimiento.value.isNotBlank() },
        { departamentoNacimiento.value.isNotBlank() },
        { ciudadNacimiento.value.isNotBlank() },
        // Domicilio de Residencia
        { paisResidencia.value.isNotBlank() },
        { departamentoResidencia.value.isNotBlank() },
        { ciudadResidencia.value.isNotBlank() },
        { direccion.value.isNotBlank() },
        // Contacto
        { telefonoCelular1.value.isNotBlank() },
        { correoElectronico.value.isNotBlank() },
        // Datos Personales
        { sexo.value.isNotBlank() },
        { genero.value.isNotBlank() },
        { grupoEtario.value.isNotBlank() }
        // Agrega más campos obligatorios según sea necesario
    )

    // Calcula el progreso como un valor entre 0f y 1f
    val progress = derivedStateOf {
        val filledFields = requiredFields.count { it() }
        if (requiredFields.isEmpty()) 0f else filledFields / requiredFields.size.toFloat()
    }
}
