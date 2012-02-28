    .section    ".text"
    .align 4
    .global scan
scan:
    save    %sp, -112, %sp  ! allocate a frame
    cmp     %i1, 0          ! (n <= 0) assuming n is 2nd parameter
    ble     .LL1            ! jump to Label 1 when true
    mov     0, %o0          ! delayed branch execution of return 0
    sll     %i1, 2, %l0     ! (localvar = n * 4) because of sizeof(int) * n
    call    malloc, 0       ! call malloc
    add     %l0, 4, %o0     ! delayed branch execution, setting malloc parameter
                            ! to (localvar + 4) this is the (n+1) part.
    ld      [%i0], %g1      ! load malloc's return address in scratch register
    st      %g1, [%o0]      ! store the address in the output register, this is the
                            ! address of the array we're returning
    mov     1, %o2          ! iterator integer i = 1
    b       .LL2            ! goto Label 2
    st      %g0, [%o0+%l0]  ! result[n] = 0, delayed branch execution
.LL3:
    ld      [%i0+%o4], %o5  ! load the value in the array using the offset
    ld      [%o0+%l0], %g1  ! load the current (result[n]) maximum, which is
                            ! stored in the last element of the array 
    add     %o4, %o0, %o3   ! increment the offset of the array. incremented
                            ! address of our newly allocated array
    cmp     %o5, %g1        ! see if we've found the max value of original array
                            ! (a[i] < result[n])
    ble     .LL4            ! jump to Label 4 when true, meaning we have not
                            ! found a new max value
    add     %o2, 1, %o2     ! i++, iterating forward, delayed branch execution
    st      %o5, [%o0+%l0]  ! this is inside the if (a[i] > result[n])
                            ! store the current value into the final slot of our
                            ! result array, as the max value
.LL4:                       ! fall down to here even when previous cmp was false
    ld      [%o3-4], %g1    ! get previous element of our new array, scratch pad it
    add     %o5, %g1, %g1   ! add it to the current element of the original array
    st      %g1, [%o0+%o4]  ! store it into the current slot of the new array
.LL2:                       ! end of for loop
    cmp     %o2, %i1        ! compare iterator i and parameter n to see if we
                            ! need to exit the for loop
    bl      .LL3            ! if (i<n) step into the for loop block
    sll     %o2, 2, %o4     ! i*4: we'll need to go 4 bytes over in the array
                            ! the result of this will be the offset into the array
.LL1:
    ret                     ! go back to main
    restore %g0, %o0, %o0   ! return the new array and reload the frame window
                            ! delayed branch execution