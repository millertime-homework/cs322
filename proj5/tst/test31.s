	.align 4
A_f:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (BINOP - (PARAM 1) (CONST 1))]
	ret
	restore
	ret
	restore

	.align 4
A_g:
!locals=0, max_args=2
	save %sp,-96,%sp
! [MOVE (TEMP 3) (CALL (NAME A_f) ( (PARAM 0) (PARAM 1)))]
	ld [%fp+68],%l0
	st %l0,[%sp+68]
	ld [%fp+72],%l1
	st %l1,[%sp+68]
	call A_f
	nop
	mov %o0,%l2
!>> Temp t3 assigned to reg %l3
	mov %o0,%l3
! [RETURN (TEMP 3)]
	ret
	restore
	ret
	restore

	.align 4
B_f:
!locals=0, max_args=0
	save %sp,-96,%sp
! [RETURN (PARAM 1)]
	ret
	restore
	ret
	restore

	.global main
	.align 4
main:
!locals=4, max_args=2
	save %sp,-112,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
	mov 2,%l0
	mov 4,%l1
	smul %l0,%l1,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t1 assigned to reg %l1
	mov %o0,%l1
! [MOVE (MEM (TEMP 1)) (CONST 1)]
	mov 1,%l0
	st %l0,[%l1]
! [MOVE (MEM (BINOP + (TEMP 1) (NAME wSZ))) (CONST 2)]
	mov 2,%l0
	mov %l1,%l2
	mov 4,%l3
	add %l2,%l3,%l2
	st %l0,[%l2]
! [MOVE (VAR 1) (TEMP 1)]
	mov %l1,%l0
	st %l0,[%fp-4]
! [MOVE (TEMP 2) (CALL (NAME malloc) ( (BINOP * (CONST 2) (NAME wSZ))))]
	mov 2,%l0
	mov 4,%l2
	smul %l0,%l2,%l0
	mov %l0,%o0
	call malloc
	nop
	mov %o0,%l0
!>> Temp t2 assigned to reg %l2
	mov %o0,%l2
! [MOVE (MEM (TEMP 2)) (CONST 1)]
	mov 1,%l0
	st %l0,[%l2]
! [MOVE (MEM (BINOP + (TEMP 2) (NAME wSZ))) (CONST 2)]
	mov 2,%l0
	mov %l2,%l3
	mov 4,%l4
	add %l3,%l4,%l3
	st %l0,[%l3]
! [MOVE (VAR 2) (TEMP 2)]
	mov %l2,%l0
	st %l0,[%fp-8]
! [MOVE (TEMP 4) (CALL (NAME A_g) ( (VAR 1) (CONST 2)))]
	ld [%fp-4],%l0
	st %l0,[%sp+68]
	ld [2],%l3
	st %l3,[%sp+68]
	call A_g
	nop
	mov %o0,%l4
!>> Temp t4 assigned to reg %l5
	mov %o0,%l5
! [MOVE (VAR 3) (TEMP 4)]
	mov %l5,%l4
	st %l4,[%fp-12]
! [MOVE (TEMP 5) (CALL (NAME A_g) ( (VAR 2) (CONST 2)))]
	ld [%fp-8],%l4
	st %l4,[%sp+68]
	ld [2],%l6
	st %l6,[%sp+68]
	call A_g
	nop
	mov %o0,%l7
!>> Temp t5 assigned to reg %i1
	mov %o0,%i1
! [MOVE (VAR 4) (TEMP 5)]
	mov %i1,%l7
	st %l7,[%fp-16]
! [CALLST (NAME print) ( (VAR 3))]
	ld [%fp-12],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
! [CALLST (NAME print) ( (VAR 4))]
	ld [%fp-16],%o1
	sethi %hi(L$1),%o0
	or %o0, %lo(L$1),%o0
	call printf
	nop
	ret
	restore

L$1:	.asciz "%d\n"

!Total regs:  10
!Total insts: 100
