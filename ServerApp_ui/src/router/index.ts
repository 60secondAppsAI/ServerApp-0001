import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Servers from '../components/Servers.vue';
import ServerDetail from '../components/ServerDetail.vue';
import Processors from '../components/Processors.vue';
import ProcessorDetail from '../components/ProcessorDetail.vue';
import MemoryModules from '../components/MemoryModules.vue';
import MemoryModuleDetail from '../components/MemoryModuleDetail.vue';
import StorageDevices from '../components/StorageDevices.vue';
import StorageDeviceDetail from '../components/StorageDeviceDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/servers',
    name: 'Servers',
    component: Servers
  },
  {
    path: '/server/:id',
    name: 'ServerDetail',
    component: ServerDetail,
    props: true
  },
  {
    path: '/processors',
    name: 'Processors',
    component: Processors
  },
  {
    path: '/processor/:id',
    name: 'ProcessorDetail',
    component: ProcessorDetail,
    props: true
  },
  {
    path: '/memoryModules',
    name: 'MemoryModules',
    component: MemoryModules
  },
  {
    path: '/memoryModule/:id',
    name: 'MemoryModuleDetail',
    component: MemoryModuleDetail,
    props: true
  },
  {
    path: '/storageDevices',
    name: 'StorageDevices',
    component: StorageDevices
  },
  {
    path: '/storageDevice/:id',
    name: 'StorageDeviceDetail',
    component: StorageDeviceDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
