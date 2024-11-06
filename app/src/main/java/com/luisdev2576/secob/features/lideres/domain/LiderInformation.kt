package com.luisdev2576.secob.features.lideres.domain

data class LiderInformation(
    val liderId: String,
    val nombreCompleto: NombreCompleto,
    val documentoIdentidad: DocumentoIdentidad,
    val fechaNacimiento: Fecha,
    val lugarNacimiento: Ubicacion,
    val residencia: Domicilio,
    val contacto: Contacto,
    val notificacion: Notificacion,
    val informacionDeContacto: TerceroDeContactoInformation?,
    val datosPersonales: DatosPersonales,
    val organizacion: Organizacion?,
    val medidaCautelar: String? // Tipo de medida cautelar si aplica
)

data class NombreCompleto(
    val primerNombre: String,
    val segundoNombre: String?,
    val apellidoPaterno: String,
    val apellidoMaterno: String?,
    val nombreIdentitario: String?
)

data class DocumentoIdentidad(
    val tipoDocumento: String, // Cédula de Ciudadanía, Cédula de Extranjería, NUIP
    val numeroDocumento: String,
    val fechaExpedicion: Fecha
)

data class Fecha(
    val dia: Int,
    val mes: Int,
    val ano: Int
)

data class Ubicacion(
    val pais: String,
    val departamento: String,
    val ciudad: String
)

data class Domicilio(
    val ubicacion: Ubicacion,
    val corregimiento: String?,
    val vereda: String?,
    val barrio: String?,
    val zona: String, // Rural o Urbana
    val direccion: String,
    val indicaciones: String?
)

data class Contacto(
    val telefonoCelular1: String,
    val telefonoCelular2: String?,
    val telefonoFijo: String?,
    val correoElectronico: String
)

data class Notificacion(
    val autorizaNotificacionesCorreo: Boolean,
    val ubicacionNotificacion: Ubicacion,
    val direccionNotificacion: String
)

data class TerceroDeContactoInformation(
    val nombresApellidos: String,
    val domicilio: Domicilio,
    val contacto: Contacto,
    val autorizaNotificacionesCorreo: Boolean
)

data class DatosPersonales(
    val sexo: String, // Hombre, Mujer, Intersexual
    val genero: String, // Femenino, Masculino, Transgénero
    val orientacionSexual: String?, // Heterosexual, Homosexual, Bisexual
    val grupoEtario: String, // Niña/Niño, Adolescente, Joven, Adulto, Adulto Mayor
    val otroFactorDiferencial: String?, // Ej. Madre Cabeza de Familia, Cuidador
    val personasACargo: Int?,
    val poseeDiscapacidad: Boolean,
    val tipoDeDiscapacidad: String?, // Física, Auditiva, Visual, etc.
    val grupoEtnico: String? // Indígena, Afrocolombiano, Palenquero, etc.
)

data class Organizacion(
    val tipoOrganizacion: String?, // Social, Gremial, Campesina, etc.
    val nombreOrganizacion: String?,
    val personaJuridicaOrganizacion: Boolean
)
