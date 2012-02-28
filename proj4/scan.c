#include <stdlib.h>

int *scan(int *a, int n)
{
  if (n <= 0)
    return 0;
  int *result = malloc (n+1);
  int sum = 0;
  result[n] = 0;
  int i;
  for (i=0; i < n; i++) {
    if (a[i] > result[n])
      result[n] = a[i];
    sum += a[i];
    result[i] = sum;
  }
  return result;
}
