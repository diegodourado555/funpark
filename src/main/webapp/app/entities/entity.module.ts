import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'grupo-maquina',
        loadChildren: () => import('./grupo-maquina/grupo-maquina.module').then(m => m.FunparkGrupoMaquinaModule)
      },
      {
        path: 'maquina',
        loadChildren: () => import('./maquina/maquina.module').then(m => m.FunparkMaquinaModule)
      },
      {
        path: 'menu',
        loadChildren: () => import('./menu/menu.module').then(m => m.FunparkMenuModule)
      },
      {
        path: 'perfil-acesso',
        loadChildren: () => import('./perfil-acesso/perfil-acesso.module').then(m => m.FunparkPerfilAcessoModule)
      },
      {
        path: 'receitas',
        loadChildren: () => import('./receitas/receitas.module').then(m => m.FunparkReceitasModule)
      },
      {
        path: 'despesas',
        loadChildren: () => import('./despesas/despesas.module').then(m => m.FunparkDespesasModule)
      },
      {
        path: 'menu-perfil',
        loadChildren: () => import('./menu-perfil/menu-perfil.module').then(m => m.FunparkMenuPerfilModule)
      },
      {
        path: 'usuario-perfil',
        loadChildren: () => import('./usuario-perfil/usuario-perfil.module').then(m => m.FunparkUsuarioPerfilModule)
      },
      {
        path: 'usuario',
        loadChildren: () => import('./usuario/usuario.module').then(m => m.FunparkUsuarioModule)
      },
      {
        path: 'loja-maquina',
        loadChildren: () => import('./loja-maquina/loja-maquina.module').then(m => m.FunparkLojaMaquinaModule)
      },
      {
        path: 'operador-caixa',
        loadChildren: () => import('./operador-caixa/operador-caixa.module').then(m => m.FunparkOperadorCaixaModule)
      },
      {
        path: 'conta-corrente',
        loadChildren: () => import('./conta-corrente/conta-corrente.module').then(m => m.FunparkContaCorrenteModule)
      },
      {
        path: 'loja',
        loadChildren: () => import('./loja/loja.module').then(m => m.FunparkLojaModule)
      },
      {
        path: 'historico-maquina',
        loadChildren: () => import('./historico-maquina/historico-maquina.module').then(m => m.FunparkHistoricoMaquinaModule)
      },
      {
        path: 'historico-operador-caixa',
        loadChildren: () =>
          import('./historico-operador-caixa/historico-operador-caixa.module').then(m => m.FunparkHistoricoOperadorCaixaModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class FunparkEntityModule {}
