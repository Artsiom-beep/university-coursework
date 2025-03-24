#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int a[1000][1000], b[10000], c[10000], nb, nc, x[10000], y[10000], m, d[10000], nd;

Convert(char s[100000]){
    int xx = 0; nb = 0;
    for (int i = 0; i < strlen(s); i++)
        if ('0' <= s[i] && s[i] <= '9')
            xx = xx * 10 + s[i] - '0';
        else{
            b[nb] = xx;
            nb++;
            xx = 0;
        }
    b[nb] = xx;
    nb++;
}

int main(){
    FILE *fin = fopen("graf.csrrg", "rt");
    int n;
    fscanf(fin, "%d", &n);
    char s1[100000], s2[100000], s3[100000], s4[100000];
    fscanf(fin, "%s", &s1);
    fscanf(fin, "%s", &s2);
    fscanf(fin, "%s", &s3);
    fscanf(fin, "%s", &s4);
    fclose(fin);
    Convert(s2);
    for (int i = nb - 1; i > 0; i--)
        b[i] -= b[i - 1];
    nc = nb;
    for (int i = 0; i < nb; i++)
        c[i] = b[i];
    Convert(s1);
    int k = 0;
    for (int i = 1; i <= n; i++){
        for (int j = 0; j < c[i]; j++)
            a[i][b[k++]] = 1;
    }
    for (int i = 1; i <= n; i++){
        printf("[");
        for (int j = 0; j < n - 1; j++)
            printf("%d, ", a[i][j]);
        printf("]\n");
    }
    Convert(s4);
    nd = nb;
    for (int i = 0; i < nb; i++)
        d[i] = b[i];
    Convert(s3);
    k = 0; int h = 0, first;
    for (int i = 0; i < nb; i++){
        if (i == d[h]){
            first = b[i];
            h++;
        } else{
            x[k] = first;
            y[k] = b[i];
            k++;
        }
    }
    for (int i = 0; i < k; i++)
        printf("%d - %d\n", x[i], y[i]);
    return 0;
}
