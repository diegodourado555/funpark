export interface IMenuPerfil {
  id?: number;
  idMenu?: number;
  idPerfil?: number;
  menuId?: number;
  perfilId?: number;
}

export class MenuPerfil implements IMenuPerfil {
  constructor(public id?: number, public idMenu?: number, public idPerfil?: number, public menuId?: number, public perfilId?: number) {}
}
