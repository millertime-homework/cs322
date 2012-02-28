#include <stdlib.h>

int *scan(int *a, int n)
{
  if (n <= 0)
    return 0;
  int *result = (int*) malloc((n+1)*sizeof(int));
  result[0] = a[0];
  result[n] = 0;
  int i;
  for (i=1; i < n; i++) {
    if (a[i] > result[n])
      result[n] = a[i];
    result[i] = a[i] + result[i-1];
  }
  return result;
}
