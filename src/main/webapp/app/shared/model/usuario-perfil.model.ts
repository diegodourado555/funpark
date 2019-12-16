export interface IUsuarioPerfil {
  id?: number;
  idUsuario?: number;
  idPerfil?: number;
  usuarioId?: number;
  perfilId?: number;
}

export class UsuarioPerfil implements IUsuarioPerfil {
  constructor(
    public id?: number,
    public idUsuario?: number,
    public idPerfil?: number,
    public usuarioId?: number,
    public perfilId?: number
  ) {}
}
