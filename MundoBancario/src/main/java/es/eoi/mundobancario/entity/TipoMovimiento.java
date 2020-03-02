package es.eoi.mundobancario.entity;

import java.util.Date;
import java.util.List;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVIMIENTOS")
public class TipoMovimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "TIPO")
	private String tipo;
}
