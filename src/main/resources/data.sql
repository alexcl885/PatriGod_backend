INSERT INTO ciudad (id, nombre, comunidad_autonoma, provincia, descripcion, fecha_patrimonio, latitud, longitud, imagen_principal) VALUES
(1, 'Córdoba', 'Andalucía', 'Córdoba',
 'Conocida por su Mezquita-Catedral y su casco histórico.',
 '1984-12-17', 37.8882, -4.7794, 'cordoba.jpg'),
(2, 'Toledo', 'Castilla-La Mancha', 'Toledo',
 'Ciudad histórica con una rica herencia cultural.',
 '1986-11-26', 39.8628, -4.0273, 'toledo.jpg'),
(3, 'Salamanca', 'Castilla y León', 'Salamanca',
 'Famosa por su universidad y arquitectura renacentista.',
 '1988-12-06', 40.9701, -5.6635, 'salamanca.jpg'),
(4, 'Ávila', 'Castilla y León', 'Ávila',
 'Ciudad amurallada con un notable conjunto histórico.',
 '1985-12-06', 40.6565, -4.6818, 'avila.jpg'),
(5, 'Santiago de Compostela', 'Galicia', 'A Coruña',
 'Destino final del Camino de Santiago, con su famosa catedral.',
 '1985-12-06', 42.8782, -8.5448, 'santiago.jpg'),
(6, 'Segovia', 'Castilla y León', 'Segovia',
 'Conocida por su acueducto romano y su casco antiguo.',
 '1985-12-06', 40.9481, -4.1184, 'segovia.jpg'),
(7, 'Cáceres', 'Extremadura', 'Cáceres',
 'Ciudad monumental con un casco antiguo bien conservado.',
 '1986-12-06', 39.4766, -6.3722, 'caceres.jpg'),
(8, 'Cuenca', 'Castilla-La Mancha', 'Cuenca',
 'Famosa por sus casas colgadas y su casco histórico.',
 '1996-12-06', 40.0704, -2.1374, 'cuenca.jpg'),
(9, 'San Cristóbal de La Laguna', 'Canarias', 'Santa Cruz de Tenerife',
 'Ejemplo de ciudad colonial no amurallada en el Atlántico.',
 '1999-12-02', 28.4874, -16.3159, 'la_laguna.jpg'),
(10, 'Ibiza', 'Islas Baleares', 'Islas Baleares',
 'Conocida por su biodiversidad y cultura.',
 '1999-12-02', 38.9088, 1.4320, 'ibiza.jpg'),
(11, 'Úbeda', 'Andalucía', 'Jaén',
 'Ciudad renacentista con un rico patrimonio arquitectónico.',
 '2003-07-03', 38.0114, -3.3700, 'ubeda.jpg'),
(12, 'Baeza', 'Andalucía', 'Jaén',
 'Ciudad renacentista con destacadas edificaciones históricas.',
 '2003-07-03', 37.9937, -3.4710, 'baeza.jpg'),
(13, 'Alcalá de Henares', 'Comunidad de Madrid', 'Madrid',
 'Ciudad natal de Cervantes con una destacada universidad.',
 '1998-12-02', 40.4818, -3.3640, 'alcala.jpg'),
(14, 'Lugo', 'Galicia', 'Lugo',
 'Famosa por su muralla romana completamente conservada.',
 '2000-12-02', 43.0097, -7.5560, 'lugo.jpg'),
(15, 'Tarragona', 'Cataluña', 'Tarragona',
 'Conjunto arqueológico romano de gran importancia.',
 '2000-12-02', 41.1189, 1.2445, 'tarragona.jpg'),
(16, 'Elche', 'Comunidad Valenciana', 'Alicante',
 'Conocida por su palmeral histórico.',
 '2000-12-02', 38.2699, -0.7126, 'elche.jpg');

INSERT INTO `usuario` (`activo`, `fecha_creacion`, `id`, `username`, `email`, `password`, `tipo`) VALUES
(b'1', NOW(), 1, 'alejandro', 'alejandro@example.com', 'hashedpassword1', 'USUARIO'),
(b'1', NOW(), 2, 'marcos', 'marcos@example.com', 'hashedpassword2', 'USUARIO'),
(b'1', NOW(), 3, 'dani', 'dani@example.com', 'hashedpassword3', 'USUARIO'),
(b'1', NOW(), 4, 'admin', 'admin@admin.com', 'adminpass', 'ADMINISTRADOR');

INSERT INTO `articulo` (`ciudad_id`, `id`, `descripcion`, `nombre`) VALUES
(1, 1, 'Monumento histórico más representativo de Córdoba.', 'Mezquita-Catedral'),
(3, 2, 'Edificio icónico de Salamanca con decoración plateresca.', 'Universidad de Salamanca');

INSERT INTO `monumento` (`articulo_id`, `id`, `imagen`) VALUES
(1, 1, 'mezquita.jpg'),
(2, 2, 'universidad.jpg');

INSERT INTO `articulo` (`ciudad_id`, `id`, `descripcion`, `nombre`) VALUES
(12, 3, 'Festival cultural renacentista.', 'Festival de Baeza'),
(11, 4, 'Concierto anual en la Sacra Capilla.', 'Concierto Coral Úbeda');

INSERT INTO `evento` (`articulo_id`, `id`, `fecha`) VALUES
(3, 3, '2025-06-10'),
(4, 4, '2025-09-15');

INSERT INTO `articulo` (`ciudad_id`, `id`, `descripcion`, `nombre`) VALUES
(7, 5, 'Tapa típica con embutidos y pan.', 'Tosta Extremeña'),
(10, 6, 'Plato típico ibicenco de mariscos.', 'Bullit de peix');

INSERT INTO `comida` (`articulo_id`, `id`, `imagen`) VALUES
(5, 5, 'tosta.jpg'),
(6, 6, 'bullit.jpg');


INSERT INTO `comentario` (`articulo_id`, `fecha`, `id`, `user_id`, `contenido`) VALUES
(1, NOW(), 1, 1, 'Impresionante arquitectura islámica.'),
(3, NOW(), 2, 2, 'Una experiencia increíble en el festival.'),
(6, NOW(), 3, 3, 'Delicioso, muy recomendable.');

INSERT INTO `puntuacion` (`puntuacion`, `articulo_id`, `id`, `user_id`) VALUES
(4.8, 1, 1, 1),
(4.5, 3, 2, 2),
(5.0, 6, 3, 3);
