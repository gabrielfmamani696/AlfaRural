package com.example.alfabetizacionrural.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "usuario")
data class UserEntity(
    // PK
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_usuario")
    val idUsuario: Int = 0,

    @ColumnInfo(name = "alias")
    val alias: String,

    @ColumnInfo(name = "avatar_id")
    val avatarId: Int,

    @ColumnInfo(name = "puntos_totales")
    val puntosTotales: Int = 0,

    @ColumnInfo(name = "racha_actual_dias")
    val rachaActualDias: Int = 0,

    // Long para timestamps
    @ColumnInfo(name = "ultima_actividad")
    val ultimaActividad: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "vio_onboarding_consumo")
    val vioOnboardingConsumo: Boolean = false,

    @ColumnInfo(name = "vio_onboarding_creacion")
    val vioOnboardingCreacion: Boolean = false,

    @ColumnInfo(name = "uuid_usuario")
    val uuidUsuario: String = UUID.randomUUID().toString()
)