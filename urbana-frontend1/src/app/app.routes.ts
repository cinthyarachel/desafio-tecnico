import { UsuarioListComponent } from './pages/usuarios/usuario-list.component';
import { UsuarioFormComponent } from './pages/usuarios/usuario-form.component';

export const routes = [
  { path: '', component: UsuarioListComponent },
  { path: 'usuarios/novo', component: UsuarioFormComponent },
  { path: 'usuarios/editar/:id', component: UsuarioFormComponent }, // rota para editar
];
