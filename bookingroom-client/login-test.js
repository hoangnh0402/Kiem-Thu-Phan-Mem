import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 50 },  // tăng lên 50 user trong 30s
    { duration: '1m', target: 100 },  // giữ ở 100 user trong 1 phút
    { duration: '30s', target: 200 }, // tăng lên 200 user trong 30s
    { duration: '30s', target: 0 },   // giảm về 0 user
  ],
};

export default function () {
  const url = 'http://localhost:8081/api/auth/login';
  const payload = JSON.stringify({
    email: 'nguyenhuyhoangpt0402@gmail.com',
    password: 'admin',
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  let res = http.post(url, payload, params);

  check(res, {
    'status is 200': (r) => r.status === 200,
    'login token received': (r) => r.json('token') !== undefined,
  });

  sleep(1);
}
